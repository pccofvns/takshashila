from argparse import ArgumentParser
from pathlib import Path
import src.scripts.open_api_enhancer as open_api_enhancer
DB_PROPERTIES = Path(__file__).parent.parent / 'database/db-dev.properties'


def init():
    props = read_properties()
    return props


def read_properties():
    props = {}
    with open(DB_PROPERTIES, 'r') as f:
        for line in f:
            line = line.rstrip()  # removes trailing whitespace and '\n' chars
            if '=' not in line:
                continue  # skips blanks and comments w/o =
            if line.startswith('#'):
                continue  # skips comments which contain =
            k, v = line.split('=', 1)
            props[k] = v
    return props


def read_file_content(file):
    with open(file, 'r') as fh:
        data = fh.read()
    return data


properties = init()
open_api_enhancer.enhance(properties)
