#ifndef COMBATE_H
#define COMBATE_H

#include "personagem.h"
#include "mobs.h"

typedef enum {
    MELEE,
    RANGED
} TipoAtaque;

void ataque_corpo_a_corpo(Personagem *p, Mob *mob);
void ataque_distancia(Personagem *p, Mob *mob);

#endif
