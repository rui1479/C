#include "personagem.h"

void usarEscada(Mapa *m, MapTileEscada escada, Personagem *personagem, int x, int y);

void init_personagem(Personagem *p, int x, int y) {
    p->x = x;
    p->y = y;
}

void move_jogador(char input, Personagem *personagem, Mapa *mapa) {
    int novo_x = personagem->x;
    int novo_y = personagem->y;
    int sobe_bloco = 1;

    switch (input) {
        case 'w': // mover para cima
            novo_y--;
            break;
        case 'a': // mover para a esquerda
            novo_x--;
            break;
        case 's': // mover para baixo
            novo_y++;
            break;
        case 'd': // mover para a direita
            novo_x++;
            break;
        case 'e': // sobe bloco
            sobe_bloco = 1;
            break;
        default: // input inválido
            return;
    }

    // Verificar se o movimento é possível
    if (novo_x >= 0 && novo_x < MAX_LARGURA && novo_y >= 0 && novo_y < MAX_ALTURA ) {

        // Atualiza a posição do jogador
        personagem->x = novo_x;
        personagem->y = novo_y;

        // Verifica se o próximo tile é uma escada
        if (mapa->cells[novo_y][novo_x].type == TILE_ESCADA) {
            usarEscada (mapa,mapa->cells[novo_y][novo_x].tile.escada_tile,personagem,novo_x, novo_y);
        }
        // Verifica se o próximo tile é uma parede
        if (mapa->cells[novo_y][novo_x].type == TILE_PAREDE) {
            int paredes_acima = 0;
            for (int i = novo_y - 1; i >= 0; i--) {
                if (mapa->cells[i][novo_x].type == TILE_PAREDE) {
                    paredes_acima++;
                } else {
                    break;
                }
            }
            if (paredes_acima == 0 || (paredes_acima >= 0 && sobe_bloco == 1)) {
                personagem->y++;
                personagem->x++;

            if (mapa->cells[personagem->y][personagem->x].type == TILE_ESCADA) {
                usarEscada(mapa,mapa->cells[novo_y][novo_x].tile.escada_tile,personagem,personagem->x, personagem->y);
            }
        }
    }
}
}

void usarEscada(Mapa *m, MapTileEscada escada, Personagem *personagem, int x, int y) {
    int direcao = escada.direcao;
    int proximoX = personagem->x;
    int proximoY = personagem->y;

    // Calcula a pŕoxima posição do jogador de acordo com a direção da escada
    if (direcao == 1) {
        proximoY--;
    } else if (direcao == 2) {
        proximoY++;
    } else if (direcao == 3) {
        proximoX--;
    } else if (direcao == 4) {
        proximoX++;
    }

    // Verifica se a próxima posição não é uma parede
    if (m->cells[proximoY][proximoX].type == TILE_PAREDE) {
        return;
    } else {
        // Move o jogador para a próxima posição
        personagem->x = proximoX;
        personagem->y = proximoY;

        // Verifica se o jogador ainda está em uma escada
        if (m->cells[proximoY][proximoX].type == TILE_ESCADA) {
            MapTileEscada nova_escada = m->cells[proximoY][proximoX].tile.escada_tile;
            usarEscada(m, nova_escada, personagem, proximoX, proximoY);
        }
    }
}

