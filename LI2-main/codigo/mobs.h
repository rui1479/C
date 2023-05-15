#ifndef MOBS_H
#define MOBS_H
#define NUM_ARMAS 4
char armas[NUM_ARMAS] = {'P', 'B', 'D', 'F'}; // P de pedra, B de bomba, D de default e F de faca

#include "mapa.h"
#include "personagem.h"
#include <stdbool.h>


typedef struct {
    int x, y,warning; // o warning é a cena do grito dos mobs. se ele estiver a 0, é porque não tam mobs na proximidade, se estiver 1, é porque tem mobs na proximidade
    char tipo;
    char armas;
} Mob;

void init_mob(Mob *mob, int x, int y, char tipo, int warning);
void update_mobs(Mapa *mapa, Personagem *personagem);
int update_mob_estupido(Mapa *mapa, Mob *mob, Personagem *personagem);
bool localizar_mobs_raio(Mapa* mapa, Mob* mob);
bool localiza_jogador_raio(Mapa* mapa, Mob* mob);
void update_mob_covarde(Mapa* mapa, Mob* mob);
void grita(Mapa* mapa, Mob* mob);
Mob* aproximar_mob_proximo(Mapa* mapa, Mob* mob);
int aproximar (Mapa* mapa, Mob* mob, Mob *mob_p);
void update_mob_estupido(Mapa* mapa, Mob* mob, Personagem* personagem);
void junta_e_aproxima(Mapa *mapa, Personagem *personagem, Mob *mob1, Mob *mob2);
void foge (Mapa *mapa, Mob *mob, Personagem *personagem);
void mover_mob_aleatoriamente(Mapa* mapa, Mob *mob);
void update_mob_esperta(mapa, mob, personagem);

#endif
