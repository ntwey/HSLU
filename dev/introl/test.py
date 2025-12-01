def my_hash(pw: str) -> str:
    # Sicherstellen, dass genau 4 Zeichen verwendet werden
    assert len(pw) == 4, "Passwort muss genau 4 Zeichen haben."

    # Zeichen in Unicode-Codes umwandeln
    c1, c2, c3, c4 = map(ord, pw)

    # Hash-Berechnung mit positionsabhängigen Gewichten
    h = c1 * 101 + c2 * 211 + c3 * 307 + c4 * 401
    h = (h * h * 97 + c1 * 13 + c2 * 17 + c3 * 19 + c4 * 23) % 100_000_000

    # Als 8-stelligen Zahlencode mit führenden Nullen zurückgeben
    return f"{h:08d}"

# Beispiel: Hash für "Haus"
password = "Haus"
hash_wert = my_hash(password)
print(password, "→", hash_wert)
