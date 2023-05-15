#include "visao.h"
#include <math.h>
#include <stdlib.h>


void atualizar_visao(Mapa *mapa, Personagem *p, int raio_visao) {
    for (int y = 0; y < MAX_ALTURA; y++) {
        for (int x = 0; x < MAX_LARGURA; x++) {
            int dx = abs(p->x - x);
            int dy = abs(p->y - y);
            double distancia = sqrt(dx * dx + dy * dy);

            if (distancia <= raio_visao) {
                mapa->tiles[y][x].tile = '.';
            } else {
                mapa->tiles[y][x].tile = '#';
            }
        }
    }
}
