# Resumen

Esta aplicación consiste en una implementación del juego de mesa [Petris](https://zacatrus.es/petris.html) en Spring como trabajo para la asignatura Diseño y Pruebas I de la ETSII de Sevilla.

# Sobre el juego

El juego es para dos jugadores, y tiene una duración aproximada de 10 minutos. Consiste en controlar un cultivo de bacterias asignado en un tablero de 7 discos de Petri distribuidos de forma hexagonal para que su reproducción sea lo menos dañina posible.

## Reglas

Cada jugador empieza con una **bacteria** en el disco de uno de los laterales del tablero. La partida se juega en 4 **rondas,** y cada una consiste en:

* **Fases de propagación:** por turnos, cada jugador elige un disco que contenga sus bacterias y mueve una o más de ellas a uno o más discos adyacentes, teniendo en cuenta que:
  * un disco no puede contener igual número de bacterias de los dos jugadores 
  * si un disco contiene 5 bacterias de un mismo jugador, se reemplazan por una **sarcina** que no puede ser retirada ni movida, y a cuyo disco no podrán moverse más bacterias de su jugador
* **Fases de fisión binaria:** tras una fase de propagación de cada jugador, se añade una bacteria a los discos que contengan bacterias de un único jugador *(pueden crearse sarcinas de esta forma)* y se pasa a la siguiente fase de propagación.
* **Fase de contaminación:** tras 3 fases de fisión binaria, los jugadores reciben un **punto de contaminación** por cada disco que contenga más bacterias suyas que del oponente *(las sarcinas cuentan como 5 bacterias).* Tras ello, empieza la siguiente ronda.

Tras terminar la cuarta fase de contaminación, la partida termina y gana el jugador que menos puntos de contaminación tenga. En caso de empate, gana el jugador con menor número de bacterias y sarcinas en los discos. Si el empate persiste, gana el jugador con menos sarcinas en los discos.

Si un jugador no puede realizar una propagación válida o llega al máximo de puntos de contaminación, perderá la partida automáticamente. Si ambos jugadores llegan al máximo de contaminación a la vez, el empate se resuelve de la forma antes descrita.

## Video de presentación de Petris

[Enlace](https://www.youtube.com/watch?v=gtE0AmNyh9A)

# Autores

* José María García Quijada
* Eloy Moreno Domínguez
* Juan Antonio Mena Vargas
* Luis Chacón Romero
* José Martín Sánchez
* Alvaro Rodríguez García
