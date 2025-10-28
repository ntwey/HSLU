#!/bin/bash
# pdf2jpg.sh – Wandelt ein PDF in JPEGs pro Seite um

# --- Prüfung auf Eingabeparameter ---
if [ $# -ne 1 ]; then
  echo "Verwendung: $0 <pdf-datei>"
  exit 1
fi

# --- Parameter & Variablen ---
INPUT_PDF="$1"
BASENAME=$(basename "$INPUT_PDF" .pdf)
OUTPUT_DIR="${BASENAME}_pages"

# --- Prüfen, ob Datei existiert ---
if [ ! -f "$INPUT_PDF" ]; then
  echo "Fehler: Datei '$INPUT_PDF' nicht gefunden."
  exit 2
fi

# --- Ausgabeverzeichnis erstellen ---
mkdir -p "$OUTPUT_DIR"

# --- Konvertierung ---
echo "Konvertiere '$INPUT_PDF' → '$OUTPUT_DIR/page-<n>.jpg' ..."

# Prüfen, ob 'pdftoppm' vorhanden ist
if command -v pdftoppm >/dev/null 2>&1; then
  pdftoppm -jpeg -r 300 "$INPUT_PDF" "$OUTPUT_DIR/page"
  echo "✅ Fertig mit pdftoppm."
elif command -v convert >/dev/null 2>&1; then
  convert -density 300 "$INPUT_PDF" "$OUTPUT_DIR/page-%02d.jpg"
  echo "✅ Fertig mit ImageMagick convert."
else
  echo "Fehler: Weder 'pdftoppm' noch 'convert' gefunden. Bitte installiere eines davon."
  echo "  → Debian/Ubuntu: sudo apt install poppler-utils"
  echo "  → macOS (Homebrew): brew install poppler"
  exit 3
fi

# --- Ausgabe ---
echo "Alle Seiten als JPEGs in '$OUTPUT_DIR' gespeichert."
