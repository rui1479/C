#ifndef PERSONAGEM_H
#define PERSONAGEM_H

#include "mapa.h"

typedef struct {
    int x, y;
} Personagem;

void init_personagem(Personagem *p, int x, int y);
void move_jogador(char input, Personagem *personagem, Mapa *mapa);

#endif
