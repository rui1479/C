#include <ncurses.h>
#include <stdlib.h>
#include "mapa.h"
#include "personagem.h"
#include "visao.h"
#include "input.h"
#include "mobs.h"

int main() {
    // Inicializa a biblioteca ncurses
    initscr();
    raw();
    keypad(stdscr, TRUE);
    noecho();

    // Inicializa o mapa, personagem e monstro
    Mapa mapa;
    gerar_mapa(&mapa);

    Personagem p;
    init_personagem(&p, 5, 5);

    Mob mob;
    init_mob(&mob, 10, 10,'E',0);

    while (1) {
        // Atualiza a visão do personagem
        atualizar_visao(&mapa, &p, 5);

        // Desenha o mapa e os personagens
        desenhar_mapa(&mapa);
        mvprintw(p.y, p.x, "@");
        mvprintw(mob.y, mob.x, "%c", mob.tipo, mob.warning);

        // Atualiza a tela
        refresh();

        // Processa a entrada e executa a ação correspondente
        Acao acao = processar_entrada();
        executar_acao(acao, &p, &mapa);

        // Implementar a lógica do monstro e outros elementos do jogo aqui
    }

    // Finaliza a biblioteca ncurses
    endwin();

    return 0;
}
