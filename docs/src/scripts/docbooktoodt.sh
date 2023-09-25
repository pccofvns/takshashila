if ! [ -x "$(command -v pandoc)" ]; then
  echo 'Error: pandoc is not installed. To install pandoc, please visit https://pandoc.org/installing.html' >&1
  exit 1
fi
mkdir -p ../odt
for f in *.xml; do pandoc "$f" -r docbook -t odt -o "../odt/${f%.xml}.odt"; done