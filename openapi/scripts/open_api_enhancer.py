import yaml
import re
import mariadb
from pathlib import Path

# Database connection parameters
db_username = 'ts'
db_password = 'ts'
db_url = 'localhost:3306/ts'
db_host = 'localhost'
db_port = '3306'
db_schema = 'ts'
pattern = r'@com\.pccofvns\.ts\.validation\.PicklistConstraint\s*\(\s*name\s*=\s*"([^"]+)"\s*\)'
append_str = '`'


def enhance(properties):
    global db_username
    global db_password
    global db_url
    global db_host
    global db_port
    global db_schema
    db_username = properties['flyway.user']
    db_password = properties['flyway.password']
    db_url = properties['flyway.url'].replace("jdbc:mariadb://", "")
    db_url_split = db_url.split(":")
    db_host = db_url_split[0]
    db_url_split = db_url_split[1].split("/")
    db_port = db_url_split[0]
    db_schema = db_url_split[1]
    # Load the OpenAPI YAML file
    with open(Path(__file__).parent.parent.parent / 'modules/ROOT/assets/attachments/takshashila.yml',
              'r') as yaml_file:
        openapi_spec = yaml.safe_load(yaml_file)
    # Iterate through the OpenAPI specification and update descriptions
    for schema in openapi_spec['components']['schemas'].items():
        schema_detail = schema[1]
        update_schema_description(schema_detail)
        if schema_detail.get('type') and schema_detail.get('properties'):
            object_schema_properties = schema_detail['properties']
            for object_field in object_schema_properties:
                update_schema_description(
                    object_schema_properties[object_field])

    with open(Path(__file__).parent.parent.parent / 'modules/ROOT/assets/attachments/takshashila.yml', 'w') as file:
        yaml.dump(openapi_spec, file)


def update_schema_description(schema_detail):
    if schema_detail.get('x-field-extra-annotation'):
        annotations = schema_detail['x-field-extra-annotation']
        match = re.search(pattern, annotations)
        if match:
            picklist_name = match.group(1)
            possible_values = fetch_picklist_values(picklist_name)
            if possible_values:
                if not schema_detail.get('example') and schema_detail.get('type') and not schema_detail[
                                                                                              'type'] == 'array':
                    schema_detail['example'] = possible_values[0]
                if not schema_detail.get('example') and schema_detail.get('type') and schema_detail['type'] == 'array':
                    schema_detail['example'] = [possible_values[0]] if len(
                        possible_values) < 2 else [possible_values[0], possible_values[1]]
                possible_values = [append_str + sub +
                                   append_str for sub in possible_values]
                schema_detail[
                    'description'] = f"{schema_detail.get('description', '')}. Possible values: {', '.join(possible_values)}"


def fetch_picklist_values(picklist_name):
    print("Processing picklist: " + picklist_name)
    with mariadb.connect(user=db_username, password=db_password,
                         host=db_host, port=db_port, database=db_schema) as connection:
        with connection.cursor() as cursor:
            values = []
            for row in cursor.execute("SELECT NAME FROM PICKLIST WHERE PICKLIST_NAME=:pn AND LOCALE='en' ORDER BY "
                                      "PICKLIST_ORDER ASC", pn=picklist_name):
                if row[0]:
                    values.append(row[0])
            return values
