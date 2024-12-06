import matplotlib.pyplot as plt

# Entrada de datos
discoinicial = int(input("Introduce la posición inicial del disco: "))
pistas = list(map(int, input("Introduce las pistas: ").split()))
modalidad = str(input("Modo (arriba/abajo): "))


# Construir la lista de pistas a visitar
pistas_ordenadas = [discoinicial]
pistas_ordenadas.extend(pistas)

# Ordenar las pistas
pistas_ordenadas.sort()

# Identificar el índice del disco inicial
indice = pistas_ordenadas.index(discoinicial)

# Listas para recorrer
recorrido = []
suma_distancias = 0

if modalidad == "arriba":
    # Ir hacia la pista más alta necesaria y luego regresar hacia la pista más baja (LOOK hacia arriba)
    recorrido.extend(pistas_ordenadas[indice:])  # Subir desde el disco inicial hasta la pista más alta requerida
    recorrido.extend(pistas_ordenadas[:indice][::-1])  # Regresar hacia la pista más baja requerida
else:
    # Ir hacia la pista más baja necesaria y luego regresar hacia la pista más alta (LOOK hacia abajo)
    recorrido.extend(pistas_ordenadas[:indice + 1][::-1])  # Bajar desde el disco inicial hasta la pista más baja requerida
    recorrido.extend(pistas_ordenadas[indice + 1:])  # Regresar hacia la pista más alta requerida

# Calcular suma de distancias
for i in range(len(recorrido) - 1):
    suma_distancias += abs(recorrido[i] - recorrido[i + 1])

# Calcular la longitud media de búsqueda
media = round(suma_distancias / len(pistas), 2)

# Mostrar los resultados
print(f"Recorrido del disco: {recorrido}")
print(f"Suma total de distancias: {suma_distancias}")
print(f"Longitud media de búsqueda: {media}")

# Gráfico del recorrido
plt.figure(figsize=(10, 6))
plt.plot(range(len(recorrido)), recorrido, marker='o', linestyle='-', linewidth=2, color='b')
plt.xticks(range(len(recorrido)), recorrido, rotation=45)
plt.yticks(sorted(set(recorrido)))
plt.title(f'LOOK (Modo: {modalidad.capitalize()})\nSuma Total de Distancias: {suma_distancias}\nLongitud Media de Búsqueda: {media}', fontsize=14)
plt.xlabel("Orden de acceso")
plt.ylabel("Número de pista")
plt.grid()
plt.tight_layout()
plt.show()
