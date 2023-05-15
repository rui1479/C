#ifndef INPUT_H
#define INPUT_H

#include "personagem.h"
#include "mapa.h"

typedef enum {
    MOVE_UP,
    MOVE_DOWN,
    MOVE_LEFT,
    MOVE_RIGHT,
    ATTACK_MELEE,
    ATTACK_RANGED,
    QUIT_GAME
} Acao;

Acao processar_entrada();
void executar_acao(Acao acao, Personagem *p, Mapa *mapa);

#endif
