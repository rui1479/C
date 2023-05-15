#include "mobs.h"
#include "mapa.h"
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
#include <limits.h>

void init_mob(Mob *mob, int x, int y, char tipo, int warning) {
    mob->x = x;
    mob->y = y;
    mob->tipo = tipo;
    mob->warning = warning;
}

void mover_mob_aleatoriamente(Mapa* mapa, Mob* mob)
{
    // Define as direções possíveis: 1 = cima, 2 = baixo, 3 = esquerda, 4 = direita
    int direcoesPossiveis[] = {1, 2, 3, 4};
    int numDirecoes = 4;
    // Obtém a posição atual do mob
    int x = mob->x;
    int y = mob->y;
    // Embaralha as direções possíveis usando o algoritmo de Fisher-Yates
    srand(time(NULL)); // Inicializa a semente aleatória
    for (int i = numDirecoes - 1; i > 0; i--) {
        int j = rand() % (i + 1);
        int temp = direcoesPossiveis[i];
        direcoesPossiveis[i] = direcoesPossiveis[j];
        direcoesPossiveis[j] = temp;
    }
    // Tenta mover o mob em cada direção aleatória até encontrar uma posição válida
    for (int i = 0; i < numDirecoes; i++) {
        int direcao = direcoesPossiveis[i];
        // Atualiza as coordenadas de acordo com a direção
        int novaX = x;
        int novaY = y;
        if (direcao == 1) { // Cima
            novaY--;
        } else if (direcao == 2) { // Baixo
            novaY++;
        } else if (direcao == 3) { // Esquerda
            novaX--;
        } else if (direcao == 4) { // Direita
            novaX++;
        }
        // Verifica se a próxima posição está dentro dos limites do mapa e se é caminhável
        if (novaX >= 0 && novaX < MAX_LARGURA && novaY >= 0 && novaY < MAX_ALTURA) {
            MapCell* proximaCelula = &(mapa->cells[novaY][novaX]);
            if (proximaCelula->type == TILE_DEFAULT && proximaCelula->tile.default_tile.walkable) {
                // Atualiza a posição do mob para a nova posição válida
                mob->x = novaX;
                mob->y = novaY;
                break; // Sai do loop quando encontrar uma posição válida
            }
        }
    }
}

void foge (Mapa *mapa, Mob *mob, Personagem *personagem)
{
    int x_diff = mob1->x - avgX;
    int y_diff = mob1->y - avgY;
    if (abs(x_diff) > abs(y_diff)) { // Movimento no eixo X
        if (x_diff > 0 && mapa->cells[mob1->y][mob1->x - 1].tile.default_tile.walkable) {
            mob1->x++;
        } else if (x_diff < 0 && mapa->cells[mob1->y][mob1->x + 1].tile.default_tile.walkable) {
            mob1->x--;
        }
    } else { // Movimento no eixo Y
        if (y_diff > 0 && mapa->cells[mob1->y - 1][mob1->x].tile.default_tile.walkable) {
            mob1->y++;
        } else if (y_diff < 0 && mapa->cells[mob1->y + 1][mob1->x].tile.default_tile.walkable) {
            mob1->y--;
        }
    }
}

void junta_e_aproxima(Mapa *mapa, Personagem *personagem, Mob *mob1, Mob *mob2)
{
    int aproximarMobs = aproximar (mapa, mob1, mob2);
    int newXA = aproximarMobs/10; // retira o algarismo das dezenas do numero que equivale à posição X para onde o mob se direciona
    int newYA = aproximarMobs%10; // retira o algarismo das unidades do numero que equivale à posição Y para onde o mob se direciona
    int aproximaDoJogador = update_mob_estupida(mapa, mob1, personagem);
    int newXU = aproximaDoJogador/10; // retira o algarismo das dezenas do numero que equivale à posição X para onde o mob se direciona
    int newYU = aproximaDoJogador%10; // retira o algarismo das unidades do numero que equivale à posição Y para onde o mob se direciona
    int avgX = (newXA + newXU) / 2; // Calcula a média das posições X
    int avgY = (newYA + newYU) / 2; // Calcula a média das posições Y
    int x_diff = mob1->x - avgX;
    int y_diff = mob1->y - avgY;
    if (abs(x_diff) > abs(y_diff)) { // Movimento no eixo X
        if (x_diff > 0 && mapa->cells[mob1->y][mob1->x - 1].tile.default_tile.walkable) {
            mob1->x--;
        } else if (x_diff < 0 && mapa->cells[mob1->y][mob1->x + 1].tile.default_tile.walkable) {
            mob1->x++;
        }
    } else { // Movimento no eixo Y
        if (y_diff > 0 && mapa->cells[mob1->y - 1][mob1->x].tile.default_tile.walkable) {
            mob1->y--;
        } else if (y_diff < 0 && mapa->cells[mob1->y + 1][mob1->x].tile.default_tile.walkable) {
            mob1->y++;
        }
    }
    x_diff = mob2->x - avgX;
    y_diff = mob2->y - avgY;
    if (abs(x_diff) > abs(y_diff)) { // Movimento no eixo X
        if (x_diff > 0 && mapa->cells[mob2->y][mob2->x - 1].tile.default_tile.walkable) {
            mob2->x--;
        } else if (x_diff < 0 && mapa->cells[mob2->y][mob2->x + 1].tile.default_tile.walkable) {
            mob2->x++;
        }
    } else { // Movimento no eixo Y
        if (y_diff > 0 && mapa->cells[mob2->y - 1][mob2->x].tile.default_tile.walkable) {
            mob2->y--;
        } else if (y_diff < 0 && mapa->cells[mob2->y + 1][mob2->x].tile.default_tile.walkable) {
            mob2->y++;
        }
    }
}

int aproximar(Mapa* mapa, Mob* mob, Mob* mob_p) { // função que junta o mob ao mob mais próximo e devolve o inteiro do ponto médio entre eles. Imaginamos que os mobs se estão a aproxma do ponto 2 3, a função retorna o inteiro 23
    int x_diff = mob->x - mob_p->x;
    int new_x = mob-> x;
    int y_diff = mob->y - mob_p->y;
    int new_y = mob -> y;
    if (abs(x_diff) > abs(y_diff)) { // Movimento no eixo X
        if (x_diff > 0 && mapa->cells[mob->y][mob->x - 1].tile.default_tile.walkable) {
            new_x--;
        } else if (x_diff < 0 && mapa->cells[mob->y][mob->x + 1].tile.default_tile.walkable) {
            new_x++;
        }
    } else { // Movimento no eixo Y
        if (y_diff > 0 && mapa->cells[mob->y - 1][mob->x].tile.default_tile.walkable) {
            new_y--;
        } else if (y_diff < 0 && mapa->cells[mob->y + 1][mob->x].tile.default_tile.walkable) {
            new_y++;
        }
    }
    // Movimento do segundo mob em direção ao primeiro
    x_diff = mob_p->x - mob->x;
    int new_x_x = mob_p->x;
    y_diff = mob_p->y - mob->y;
    int new_y_y = mob->y;
    if (abs(x_diff) > abs(y_diff)) { // Movimento no eixo X
        if (x_diff > 0 && mapa->cells[mob_p->y][mob_p->x - 1].tile.default_tile.walkable) {
            new_x_x--;
        } else if (x_diff < 0 && mapa->cells[mob_p->y][mob_p->x + 1].tile.default_tile.walkable) {
            new_x_x++;
        }
    } else { // Movimento no eixo Y
        if (y_diff > 0 && mapa->cells[mob_p->y - 1][mob_p->x].tile.default_tile.walkable) {
            new_y_y--;
        } else if (y_diff < 0 && mapa->cells[mob_p->y + 1][mob_p->x].tile.default_tile.walkable) {
            new_y_y++;
        }
    }
    // Construção do inteiro com as coordenadas do ponto de aproximação
    int x = (new_x + new_x_x) % 10; // Obter o dígito das unidades do X
    int y = (new_y + new_y_y) % 10; // Obter o dígito das unidades do Y
    int xy = x * 10 + y; // Combinar os dígitos em um número inteiro
    return xy;
}

Mob* aproximar_mob_proximo(Mapa* mapa, Mob* mob) {
    int raio = 5;
    int x_min = max(0, mob->x - raio);
    int x_max = min(MAX_LARGURA - 1, mob->x + raio);
    int y_min = max(0, mob->y - raio);
    int y_max = min(MAX_ALTURA - 1, mob->y + raio);
    Mob* mob_proximo = NULL;
    int distancia_proximo = INT_MAX;
    for (int y = y_min; y <= y_max; y++) {
        for (int x = x_min; x <= x_max; x++) {
            MapCell* celula = &mapa->cells[y][x];
            if (celula->type == TILE_DEFAULT || celula->type == TILE_PAREDE || celula->type == TILE_ESCADA) {
                continue;
            }
            Mob* outro_mob = (Mob*) celula->tile.default_tile.walkable;
            if (outro_mob == NULL || outro_mob == mob) {
                continue;
            }
            int distancia_x = abs(mob->x - outro_mob->x);
            int distancia_y = abs(mob->y - outro_mob->y);
            int distancia = distancia_x + distancia_y;
            if (distancia <= raio && distancia < distancia_proximo) {
                distancia_proximo = distancia;
                mob_proximo = outro_mob;
            }
        }
    }
    return mob_proximo;
}

void grita(Mapa* mapa, Mob* mob) { // função que avisa na vizinhança do mob que o player está na vizinhança do mob em questão
    int raio = 5;
    int x_min = fmax(mob->x - raio, 0);
    int y_min = fmax(mob->y - raio, 0);
    int x_max = fmin(mob->x + raio, MAX_LARGURA - 1);
    int y_max = fmin(mob->y + raio, MAX_ALTURA - 1);
    
    for (int y = y_min; y <= y_max; y++) {
        for (int x = x_min; x <= x_max; x++) {
            MapCell* celula = &mapa->cells[y][x];
            if (celula->type == TILE_DEFAULT) {
                continue;
            }
            if (celula->type == TILE_PAREDE) {
                continue;
            }
            if (celula->type == TILE_ESCADA) {
                continue;
            }
            if (celula->tile.default_tile.walkable == NULL) {
                continue;
            }
            Mob* outro_mob = (Mob*) celula->tile.default_tile.walkable;
            if (outro_mob == NULL || outro_mob == mob) {
                continue;
            }
            int distancia_x = abs(mob->x - outro_mob->x);
            int distancia_y = abs(mob->y - outro_mob->y);
            if (distancia_x <= raio && distancia_y <= raio) {
                outro_mob->warning = 1;
            }
        }
    }
}

bool localiza_jogador_raio(Mapa* mapa, Mob* mob, Personagem *personagem) {
    if (abs(mob->x - personagem->x)<=5 || abs(mob->y-personagem->y)<=5)
    return true; 
    else 
    return false;
}

bool localizar_mobs_raio(Mapa* mapa, Mob* mob) { // função que localiza os mobs dentro do raio/visão
    int raio = 5;
    int x_min = max(0, mob->x - raio);
    int x_max = min(MAX_LARGURA - 1, mob->x + raio);
    int y_min = max(0, mob->y - raio);
    int y_max = min(MAX_ALTURA - 1, mob->y + raio);

    for (int y = y_min; y <= y_max; y++) {
        for (int x = x_min; x <= x_max; x++) {
            MapCell* celula = &mapa->cells[y][x];
            if (celula->type == TILE_DEFAULT) {
                continue;
            }
            if (celula->type == TILE_PAREDE) {
                continue;
            }
            if (celula->type == TILE_ESCADA) {
                continue;
            }
            Mob* outro_mob = (Mob*) celula->tile.default_tile.walkable;
            if (outro_mob == NULL || outro_mob == mob) {
                continue;
            }
            int distancia_x = abs(mob->x - outro_mob->x);
            int distancia_y = abs(mob->y - outro_mob->y);
            if (distancia_x <= raio && distancia_y <= raio) {
                return true;
            }
        }
    }
    return false;
}

void update_mob_estupido(Mapa* mapa, Mob* mob, Personagem* personagem) {// função que move o mob em questão
    int target = update_mob_estupida(mapa, mob, personagem);
    int target_x = target / 10;
    int target_y = target % 10;
    int x_diff = mob->x - target_x;
    int y_diff = mob->y - target_y;
    if (abs(x_diff) > abs(y_diff)) { // Movimento no eixo X
        if (x_diff > 0 && mapa->cells[mob->y][mob->x - 1].tile.default_tile.walkable) {
            mob->x--;
        } else if (x_diff < 0 && mapa->cells[mob->y][mob->x + 1].tile.default_tile.walkable) {
            mob->x++;
        }
    } else { // Movimento no eixo Y
        if (y_diff > 0 && mapa->cells[mob->y - 1][mob->x].tile.default_tile.walkable) {
            mob->y--;
        } else if (y_diff < 0 && mapa->cells[mob->y + 1][mob->x].tile.default_tile.walkable) {
            mob->y++;
        }
    }
}

void update_mob_cobarde(Mapa* mapa, Mob* mob, Personagem* personagem)
{
    bool jogadorProx = localiza_jogador_raio(mapa, mob, personagem);
    bool mobProx = localizar_mobs_raio(mapa, mob);
    
    if (!mobProx && !jogadorProx)
    {
        mover_mob_aleatoriamente(mapa, mob);
    }
    else if (!mobProx && jogadorProx)
    {
        grita(mapa, mob);
        foge(mapa, mob, personagem);
    }
    else if (mobProx && !jogadorProx)
    {
        Mob* mob_proximo = aproximar_mob_proximo(mapa, mob);
        // Faça algo com o mob_proximo, se necessário
    }
    else if (mobProx && jogadorProx)
    {
        grita(mapa, mob);
        Mob* mob_proximo = aproximar_mob_proximo(mapa, mob);
        junta_e_aproxima(mapa, personagem, mob, mob_proximo);
    }
}

void update_mob_esperta(mapa, mob, personagem)
{
    bool mob_Prox = localizar_mobs_raio(mapa, mob);
    bool jog_Prox = localiza_jogador_raio(mapa, mob, personagem);
    
}


int update_mob_estupida(Mapa* mapa, Mob* mob, Personagem* personagem) { // função que calcula para onde o mob se moverá e retorna o ponto médio entre o jogador e o mob
    int x_diff = personagem->x - mob->x;
    int y_diff = personagem->y - mob->y;
    int new_x = mob -> x;
    int new_y = mob -> y;
    if (abs(x_diff) > abs(y_diff)) { // Movimento no eixo X
        if (x_diff > 0 && mapa->cells[mob->y][mob->x + 1].tile.default_tile.walkable) {
            new_x++;
        } else if (x_diff < 0 && mapa->cells[mob->y][mob->x - 1].tile.default_tile.walkable) {
            new_x--;
        }
    } else { // Movimento no eixo Y
        if (y_diff > 0 && mapa->cells[mob->y + 1][mob->x].tile.default_tile.walkable) {
            new_y++;
        } else if (y_diff < 0 && mapa->cells[mob->y - 1][mob->x].tile.default_tile.walkable) {
            new_y--;
        }
    }
    
    int target = 0;
    if (new_x == personagem->x || new_y == personagem->y) {
        target = mob->x * 10 + mob->y;
    }
    return target;
}

void update_mobs(Mapa* mapa, Personagem *personagem) { // função que atualiza todos os mobs no mapa
    for (int y = 0; y < MAX_ALTURA; y++) {
        for (int x = 0; x < MAX_LARGURA; x++) {
            MapCell* celula = &mapa->cells[y][x];
            if (celula->type == TILE_DEFAULT) {
                continue;
            }
            if (celula->type == TILE_PAREDE) {
                continue;
            }
            if (celula->type == TILE_ESCADA) {
                continue;
            }
            Mob* mob = (Mob*) celula->tile.default_tile.walkable;
            if (mob == NULL) {
                continue;
            }
            switch (mob->tipo) {
                case 'E':
                    update_mob_estupido(mapa, mob, personagem);
                    break;
                case 'C':
                    update_mob_cobarde(mapa, mob, personagem);
                    break;
                case 'P':
                    update_mob_esperta(mapa, mob, personagem);
                    break;
                default:
                    break;
            }
        }
    }
}


int aproximar(Mapa* mapa, Mob* mob, Mob* mob_p) { // função que junta o mob ao mob mais próximo e devolve o inteiro do ponto médio entre eles. Imaginamos que os mobs se estão a aproxma do ponto 2 3, a função retorna o inteiro 23
    int x_diff = mob->x - mob_p->x;
    int new_x = mob-> x;
    int y_diff = mob->y - mob_p->y;
    int new_y = mob -> y;
    if (abs(x_diff) > abs(y_diff)) { // Movimento no eixo X
        if (x_diff > 0 && mapa->cells[mob->y][mob->x - 1].tile.default_tile.walkable) {
            new_x--;
        } else if (x_diff < 0 && mapa->cells[mob->y][mob->x + 1].tile.default_tile.walkable) {
            new_x++;
        }
    } else { // Movimento no eixo Y
        if (y_diff > 0 && mapa->cells[mob->y - 1][mob->x].tile.default_tile.walkable) {
            new_y--;
        } else if (y_diff < 0 && mapa->cells[mob->y + 1][mob->x].tile.default_tile.walkable) {
            new_y++;
        }
    }

    // Movimento do segundo mob em direção ao primeiro
    x_diff = mob_p->x - mob->x;
    int new_x_x = mob_p->x;
    y_diff = mob_p->y - mob->y;
    int new_y_y = mob->y;
    if (abs(x_diff) > abs(y_diff)) { // Movimento no eixo X
        if (x_diff > 0 && mapa->cells[mob_p->y][mob_p->x - 1].tile.default_tile.walkable) {
            new_x_x--;
        } else if (x_diff < 0 && mapa->cells[mob_p->y][mob_p->x + 1].tile.default_tile.walkable) {
            new_x_x++;
        }
    } else { // Movimento no eixo Y
        if (y_diff > 0 && mapa->cells[mob_p->y - 1][mob_p->x].tile.default_tile.walkable) {
            new_y_y--;
        } else if (y_diff < 0 && mapa->cells[mob_p->y + 1][mob_p->x].tile.default_tile.walkable) {
            new_y_y++;
        }
    }

    // Construção do inteiro com as coordenadas do ponto de aproximação
    int x = (new_x + new_x_x) % 10; // Obter o dígito das unidades do X
    int y = (new_y + new_y_y) % 10; // Obter o dígito das unidades do Y
    int xy = x * 10 + y; // Combinar os dígitos em um número inteiro

    return xy;
}


void aproximar_mob_proximo(Mapa* mapa, Mob* mob) {
    int raio = 5;
    int x_min = max(0, mob->x - raio);
    int x_max = min(MAX_LARGURA - 1, mob->x + raio);
    int y_min = max(0, mob->y - raio);
    int y_max = min(MAX_ALTURA - 1, mob->y + raio);

    Mob* mob_proximo = NULL;
    int distancia_proximo = INT_MAX;

    for (int y = y_min; y <= y_max; y++) {
        for (int x = x_min; x <= x_max; x++) {
            MapCell* celula = &mapa->cells[y][x];
            if (celula->type == TILE_DEFAULT || celula->type == TILE_PAREDE || celula->type == TILE_ESCADA) {
                continue;
            }
            Mob* outro_mob = (Mob*) celula->tile.default_tile.walkable;
            if (outro_mob == NULL || outro_mob == mob) {
                continue;
            }
            int distancia_x = abs(mob->x - outro_mob->x);
            int distancia_y = abs(mob->y - outro_mob->y);
            int distancia = distancia_x + distancia_y;
            if (distancia <= raio && distancia < distancia_proximo) {
                distancia_proximo = distancia;
                mob_proximo = outro_mob;
            }
        }
    }

    if (mob_proximo != NULL) {
        aproximar(mapa, mob, mob_proximo);
    }
}


void grita(Mapa* mapa, Mob* mob) { // função que avisa na vizinhança do mob que p player está na vizinhança do mob em questão
    int raio = 5;
    int x_min = fmax(mob->x - raio, 0);
    int y_min = fmax(mob->y - raio, 0);
    int x_max = fmin(mob->x + raio, MAX_LARGURA - 1);
    int y_max = fmin(mob->y + raio, MAX_ALTURA - 1);
    
    for (int y = y_min; y <= y_max; y++) {
        for (int x = x_min; x <= x_max; x++) {
            MapCell* celula = &mapa->cells[y][x];
            if (celula->type == TILE_DEFAULT) {
                continue;
            }
            if (celula->type == TILE_PAREDE) {
                continue;
            }
            if (celula->type == TILE_ESCADA) {
                continue;
            }
            if (celula->tile.default_tile.walkable == NULL) {
                continue;
            }
            Mob* outro_mob = (Mob*) celula->tile.default_tile.walkable;
            if (outro_mob == NULL || outro_mob == mob) {
                continue;
            }
            int distancia_x = abs(mob->x - outro_mob->x);
            int distancia_y = abs(mob->y - outro_mob->y);
            if (distancia_x <= raio && distancia_y <= raio) {
                outro_mob->warning = 1;
            }
        }
    }
}


bool localiza_jogador_raio(Mapa* mapa, Mob* mob) {
    int raio = 5;
    int x_min = fmax(mob->x - raio, 0);
    int y_min = fmax(mob->y - raio, 0);
    int x_max = fmin(mob->x + raio, MAX_LARGURA - 1);
    int y_max = fmin(mob->y + raio, MAX_ALTURA - 1);
    
    for (int y = y_min; y <= y_max; y++) {
        for (int x = x_min; x <= x_max; x++) {
            MapCell* celula = &mapa->cells[y][x];
            if (celula->type == TILE_DEFAULT) {
                continue;
            }
            if (celula->type == TILE_PAREDE) {
                continue;
            }
            if (celula->type == TILE_ESCADA) {
                continue;
            }
            if (celula->tile.default_tile.walkable != NULL) {
                continue;
            }
            if (x == mob->x && y == mob->y) {
                continue;
            }
            if (celula->tile.default_tile.walkable == NULL && celula->tile.default_tile.tile == '@') {
                return true;
            }
        }
    }
    return false;
}

bool localizar_mobs_raio(Mapa* mapa, Mob* mob) { // função que localiza os mobs dentro do raio/visão
    int raio = 5;
    int x_min = max(0, mob->x - raio);
    int x_max = min(MAX_LARGURA - 1, mob->x + raio);
    int y_min = max(0, mob->y - raio);
    int y_max = min(MAX_ALTURA - 1, mob->y + raio);

    for (int y = y_min; y <= y_max; y++) {
        for (int x = x_min; x <= x_max; x++) {
            MapCell* celula = &mapa->cells[y][x];
            if (celula->type == TILE_DEFAULT) {
                continue;
            }
            if (celula->type == TILE_PAREDE) {
                continue;
            }
            if (celula->type == TILE_ESCADA) {
                continue;
            }
            Mob* outro_mob = (Mob*) celula->tile.default_tile.walkable;
            if (outro_mob == NULL || outro_mob == mob) {
                continue;
            }
            int distancia_x = abs(mob->x - outro_mob->x);
            int distancia_y = abs(mob->y - outro_mob->y);
            if (distancia_x <= raio && distancia_y <= raio) {
                return true;
            }
        }
    }

    return false;
}

void update_mob_estupido(Mapa* mapa, Mob* mob, Personagem* personagem) {
    int target = update_mob_estupida(mapa, mob, personagem);
    int target_x = target / 10;
    int target_y = target % 10;
    
    if (target_x != mob->x || target_y != mob->y) {
        mob->x = target_x;
        mob->y = target_y;
    }
}


int update_mob_estupida(Mapa* mapa, Mob* mob, Personagem* personagem) { // função que calcula para onde o mob se moverá e retorna o ponto médio entre o jogador e o mob
    int x_diff = personagem->x - mob->x;
    int y_diff = personagem->y - mob->y;
    int new_x = mob -> x;
    int new_y = mob -> y;
    if (abs(x_diff) > abs(y_diff)) { // Movimento no eixo X
        if (x_diff > 0 && mapa->cells[mob->y][mob->x + 1].tile.default_tile.walkable) {
            new_x++;
        } else if (x_diff < 0 && mapa->cells[mob->y][mob->x - 1].tile.default_tile.walkable) {
            new_x--;
        }
    } else { // Movimento no eixo Y
        if (y_diff > 0 && mapa->cells[mob->y + 1][mob->x].tile.default_tile.walkable) {
            new_y++;
        } else if (y_diff < 0 && mapa->cells[mob->y - 1][mob->x].tile.default_tile.walkable) {
            new_y--;
        }
    }
    
    int target = 0;
    if (new_x == personagem->x || new_y == personagem->y) {
        target = mob->x * 10 + mob->y;
    }
    return target;
}


void update_mobs(Mapa* mapa, Personagem *personagem) { // função que atualiza todos os mobs no mapa
    for (int y = 0; y < MAX_ALTURA; y++) {
        for (int x = 0; x < MAX_LARGURA; x++) {
            MapCell* celula = &mapa->cells[y][x];
            if (celula->type == TILE_DEFAULT) {
                continue;
            }
            if (celula->type == TILE_PAREDE) {
                continue;
            }
            if (celula->type == TILE_ESCADA) {
                continue;
            }
            Mob* mob = (Mob*) celula->tile.default_tile.walkable;
            if (mob == NULL) {
                continue;
            }
            switch (mob->tipo) {
                case 'E':
                    update_mob_estupida(mob, mapa, personagem);
                    break;
                case 'C':
                    update_mob_cobarde(mob, mapa, personagem);
                    break;
                case 'P':
                    update_mob_esperta(mob, mapa, personagem);
                    break;
                default:
                    break;
            }
        }
    }
}
