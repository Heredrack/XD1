import matplotlib.pyplot as plt

# Entrada de datos
discoinicial = int(input("Introduce la posición inicial del disco: "))
pistas = list(map(int, input("Introduce las pistas: ").split()))
modalidad = str(input("Modo (arriba/abajo): "))
tope = int(input("Tope del disco: "))
piso = int(input("Piso del disco: "))

# Construir la lista de pistas a visitar
pistas_ordenadas = [discoinicial]
pistas_ordenadas.extend(pistas)
pistas_ordenadas.append(tope)  # Agregar el tope
pistas_ordenadas.append(piso)  # Agregar el piso

# Ordenar las pistas
pistas_ordenadas.sort()

# Identificar el índice del disco inicial
indice = pistas_ordenadas.index(discoinicial)

# Listas para recorrer
recorrido = []
suma_distancias = 0

if modalidad == "arriba":
    # Ir hacia el tope y luego regresar hacia el piso (SCAN hacia arriba)
    recorrido.extend(pistas_ordenadas[indice:])  # Subir desde el disco inicial hasta el tope
    recorrido.extend(pistas_ordenadas[:indice][::-1])  # Regresar hacia el piso
else:
    # Ir hacia el piso y luego regresar hacia el tope (SCAN hacia abajo)
    recorrido.extend(pistas_ordenadas[:indice + 1][::-1])  # Bajar desde el disco inicial hasta el piso
    recorrido.extend(pistas_ordenadas[indice + 1:])  # Regresar hacia el tope

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
plt.plot(range(len(recorrido)), recorrido, marker='o', linestyle='-', linewidth=2, color='g')
plt.xticks(range(len(recorrido)), recorrido, rotation=45)
plt.yticks(sorted(set(recorrido)))
plt.title(f'SCAN (Modo: {modalidad.capitalize()})\nSuma Total de Distancias: {suma_distancias}\nLongitud Media de Búsqueda: {media}', fontsize=14)
plt.xlabel("Orden de acceso")
plt.ylabel("Número de pista")
plt.grid()
plt.tight_layout()
plt.show()