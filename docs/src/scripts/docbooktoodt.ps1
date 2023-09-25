if (Get-Command "pandoc.exe" -ErrorAction SilentlyContinue) 
{ 
    New-Item -ItemType Directory -Force -Path ../odt
    gci -r -i *.xml |foreach{$odt=$_.directoryname+"\..\odt\"+$_.basename+".odt";pandoc -r docbook -t odt -s $_.name -o $odt}
}
if ((Get-Command "pandoc.exe" -ErrorAction SilentlyContinue) -eq $null) 
{ 
   Write-Host "Unable to find pandoc.exe in your PATH"
}