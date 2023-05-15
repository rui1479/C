#include "input.h"
#include "combate.h"
#include <ncurses.h>
#include <stdlib.h>


Acao processar_entrada() {
    int ch = getch();

    switch (ch) {
        case KEY_UP:
            return MOVE_UP;
        case KEY_DOWN:
            return MOVE_DOWN;
        case KEY_LEFT:
            return MOVE_LEFT;
        case KEY_RIGHT:
            return MOVE_RIGHT;
        case 'a':
            return ATTACK_MELEE;
        case 's':
            return ATTACK_RANGED;
        case 'q':
            return QUIT_GAME;
        default:
            return -1;
    }
}

void executar_acao(Acao acao, Personagem *p, Mapa *mapa) {
    switch (acao) {
        case MOVE_UP:
            move_personagem(p, 0, -1, mapa);
            break;
        case MOVE_DOWN:
            move_personagem(p, 0, 1, mapa);
            break;
        case MOVE_LEFT:
            move_personagem(p, -1, 0, mapa);
            break;
        case MOVE_RIGHT:
            move_personagem(p, 1, 0, mapa);
            break;
        // Incluir lógica para ataque corpo a corpo e ataques à distância aqui
        // Por exemplo, encontrar o mob alvo e chamar as funções de ataque do arquivo combate.c
        case QUIT_GAME:
            endwin();
            exit(0);
        default:
            break;
    }
}
//