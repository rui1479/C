CC = gcc
CFLAGS = -Wall -Wextra -pedantic -O2
LIBS = -lncurses -lm
OBJS = main.o mapa.o personagem.o visao.o input.o mobs.o combate.o

jogo: $(OBJS)
	$(CC) $(CFLAGS) -o jogo $(OBJS) $(LIBS)

main.o: main.c mapa.h personagem.h visao.h input.h mobs.h
	$(CC) $(CFLAGS) -c main.c

mapa.o: mapa.c mapa.h
	$(CC) $(CFLAGS) -c mapa.c

personagem.o: personagem.c personagem.h mapa.h
	$(CC) $(CFLAGS) -c personagem.c

visao.o: visao.c visao.h mapa.h personagem.h
	$(CC) $(CFLAGS) -c visao.c

input.o: input.c input.h personagem.h mapa.h
	$(CC) $(CFLAGS) -c input.c

mobs.o: mobs.c mobs.h mapa.h personagem.h
	$(CC) $(CFLAGS) -c mobs.c

combate.o: combate.c combate.h personagem.h mobs.h
	$(CC) $(CFLAGS) -c combate.c

clean:
	rm -f *.o jogo
