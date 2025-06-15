#!/bin/bash

# Percorso alla cartella JavaFX
JAVAFX_LIB="javafx-sdk-24.0.1/lib"

# Compilazione di tutti i file Java, includendo JavaFX e librerie locali
echo "🔧 Compilazione in corso..."
javac --module-path $JAVAFX_LIB --add-modules javafx.controls,javafx.fxml -cp ".:lib/*" $(find src -name "*.java")
if [ $? -eq 0 ]; then
    echo "✅ Compilazione completata con successo."
else
    echo "❌ Errore durante la compilazione."
    exit 1
fi

# Esecuzione del programma
echo "🚀 Avvio dell'applicazione..."
java --module-path $JAVAFX_LIB --add-modules javafx.controls,javafx.fxml -cp ".:lib/*:src" Main
