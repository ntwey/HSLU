#!/bin/bash
# ocr_merge.sh – Führt OCR auf allen JPG-Seiten aus und kombiniert sie in eine Datei

# Voraussetzung: Tesseract OCR installiert
#   Debian/Ubuntu:  sudo apt install tesseract-ocr
#   macOS (Homebrew): brew install tesseract
#   Windows (WSL):    sudo apt install tesseract-ocr

OUTPUT_FILE="output.txt"
LANG="deu+eng"   # Deutsch + Englisch

# Bestehende Datei löschen
rm -f "$OUTPUT_FILE"

# Durch alle Seiten iterieren (sortiert)
for img in $(ls page-*.jpg | sort -V); do
  echo "🧠 Verarbeite $img ..."
  tesseract "$img" temp -l "$LANG" --psm 1 txt
  echo -e "\n\n===== Seite ${img%.jpg} =====\n" >> "$OUTPUT_FILE"
  cat temp.txt >> "$OUTPUT_FILE"
  echo -e "\n\n" >> "$OUTPUT_FILE"
done

# Temporäre Datei löschen
rm -f temp.txt

echo "✅ OCR abgeschlossen. Gesamter Text in '$OUTPUT_FILE' gespeichert."
