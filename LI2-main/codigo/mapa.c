#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "mapa.h"
#include "personagem.h"
#include <stdbool.h>

bool local_escada_possivel(Mapa *mapa,int linha, int coluna, int direcao);

// Depois de o mapa ter sido gerado aleatóreamente, esta função imprime-o
void desenha_mapa(Mapa *mapa, Personagem *personagem) {
    int i, j;
    for (i = 0; i < MAX_ALTURA; i++) {
        for (j = 0; j < MAX_LARGURA; j++) {
            if (i == personagem->y && j == personagem->x) {
                printf("@");
            } else if (mapa->cells[i][j].type == TILE_PAREDE) {
                printf("#");
            } else if (mapa->cells[i][j].type == TILE_ESCADA) {
                if (mapa->cells[i][j].tile.escada_tile.direcao == 1) {
                    printf("^");
                } else if (mapa->cells[i][j].tile.escada_tile.direcao == 2) {
                    printf("v");
                } else if (mapa->cells[i][j].tile.escada_tile.direcao == 3) {
                    printf("<");
                } else if (mapa->cells[i][j].tile.escada_tile.direcao == 4) {
                    printf(">");
                } else {
                    printf(".");
                }
            }
        }
        printf("\n");
    }
}

void gerar_mapa(Mapa *mapa) {
    // Inicializa a semente do gerador aleatório
    srand(time(NULL));

    // Define o tamanho do mapa sem contar com as bordas
    int largura = MAX_LARGURA - 4;
    int altura = MAX_ALTURA - 4;

    // Preenche o mapa com defaults
    for (int i = 0; i < altura; i++) {
        for (int j = 0; j < largura; j++) {
            mapa->cells[i+2][j+2].type = TILE_DEFAULT;
            mapa->cells[i+2][j+2].tile.default_tile.tile = '.';
            mapa->cells[i+2][j+2].tile.default_tile.walkable = 1;
        }
    }

    // Preenche as bordas de cima e de baixo do mapa com paredes
    for (int i = 0; i < MAX_ALTURA; i++) {
        mapa->cells[i][0].type = TILE_PAREDE;
        mapa->cells[i][0].tile.parede_tile.tile = '#';
        mapa->cells[i][0].tile.parede_tile.climbable = 0;
        mapa->cells[i][1].type = TILE_PAREDE;
        mapa->cells[i][1].tile.parede_tile.tile = '#';
        mapa->cells[i][1].tile.parede_tile.climbable = 0;
        mapa->cells[i][MAX_LARGURA-2].type = TILE_PAREDE;
        mapa->cells[i][MAX_LARGURA-2].tile.parede_tile.tile = '#';
        mapa->cells[i][MAX_LARGURA-2].tile.parede_tile.climbable = 0;
        mapa->cells[i][MAX_LARGURA-1].type = TILE_PAREDE;
        mapa->cells[i][MAX_LARGURA-1].tile.parede_tile.tile = '#';
        mapa->cells[i][MAX_LARGURA-1].tile.parede_tile.climbable = 0;
    }

    // Preenche as bordas da esquerda e da direita do mapa com paredes
    for (int j = 0; j < MAX_LARGURA; j++) {
        mapa->cells[0][j].type = TILE_PAREDE;
        mapa->cells[0][j].tile.parede_tile.tile = '#';
        mapa->cells[0][j].tile.parede_tile.climbable = 0;
        mapa->cells[1][j].type = TILE_PAREDE;
        mapa->cells[1][j].tile.parede_tile.tile = '#';
        mapa->cells[1][j].tile.parede_tile.climbable = 0;
        mapa->cells[MAX_ALTURA-2][j].type = TILE_PAREDE;
        mapa->cells[MAX_ALTURA-2][j].tile.parede_tile.tile = '#';
        mapa->cells[MAX_ALTURA-2][j].tile.parede_tile.climbable = 0;
        mapa->cells[MAX_ALTURA-1][j].type = TILE_PAREDE;
        mapa->cells[MAX_ALTURA-1][j].tile.parede_tile.tile = '#';
        mapa->cells[MAX_ALTURA-1][j].tile.parede_tile.climbable = 0;
    }

    // Gera as paredes aleatoriamente
    for (int i = 2; i < altura+2; i++) {
        for (int j = 2; j < largura+2; j++) {
            int rand_num = rand() % 100;
            if (rand_num <= 39) {
                mapa->cells[i][j].type = TILE_PAREDE;
                mapa->cells[i][j].tile.parede_tile.tile = '#';
                mapa->cells[i][j].tile.parede_tile.climbable = 0;
            }
        }
    }

    gerar_escadas_aleatorias(mapa);
    
    // Executa a função gerar_parede 4 vezes
    for (int i = 4; i>0; i--) {
        gerar_parede(mapa);
    }

}

void gerar_parede (Mapa *mapa) {
    for (int i = 2; i < MAX_ALTURA+2; i++) {
        for (int j = 2; j < MAX_LARGURA+2; j++) {
            
            // Verifica as 9 células ao redor
            int count = 0;
            for (int k = i-1; k <= i+1; k++) {
                for (int l = j-1; l <= j+1; l++) {
                    if (mapa->cells[k][l].type == TILE_PAREDE) {
                        count++;
                    }
                }
            }
            
            // Define como parede se tiver 5 ou mais paredes vizinhas
            if (count >= 5) {
                mapa->cells[i][j].type = TILE_PAREDE;
                mapa->cells[i][j].tile.parede_tile.tile = '#';
                mapa->cells[i][j].tile.parede_tile.climbable = 0;
            }
        }
    }   
}

 // Função que gera 1 escada em qualquer coluna, mas só gera de 3 em 3 linhas
void gerar_escadas_aleatorias(Mapa *mapa) {
    int i, linha, coluna, direcao;
    int num_escadas = MAX_LARGURA/3;
    for (i = 0; i < num_escadas; i++) {
        // Escolher aleatoriamente uma linha a cada 3 linhas
        linha = 3 * (rand() % (MAX_ALTURA / 3));
        
        // Escolher aleatoriamente uma célula na linha escolhida
        do {
            coluna = rand() % MAX_LARGURA;
        } while (mapa->cells[linha][coluna].type == TILE_PAREDE);
        
        // Definir a célula escolhida como uma escada com direção aleatória
        direcao = rand() % 4 + 1; // 1 = cima, 2 = baixo, 3 = esquerda, 4 = direita
        if (local_escada_possivel(mapa, linha, coluna, direcao)) {
            mapa->cells[linha][coluna].type = TILE_ESCADA;
            mapa->cells[linha][coluna].tile.escada_tile.direcao = direcao;
        }
    }
}

// Função que verifica se a posicação da escada é válida, ou seja, se não tem uma escada virada para cima e diretamente em cima da escada tem uma parede
bool local_escada_possivel(Mapa *mapa, int linha, int coluna, int direcao) { 
    // Verifica se a linha e coluna fornecidas estão dentro dos limites do mapa
    if (linha < 1 || linha >= MAX_ALTURA - 1 || coluna < 1 || coluna >= MAX_LARGURA - 1) {
        return false;
    }

    // Verifica se na direção da escada existe uma parede
    if (direcao == 1 && mapa->cells[linha - 1][coluna].type == TILE_PAREDE) {
        return false;
    } else if (direcao == 2 && mapa->cells[linha + 1][coluna].type == TILE_PAREDE) {
        return false;
    } else if (direcao == 3 && mapa->cells[linha][coluna - 1].type == TILE_PAREDE) {
        return false;
    } else if (direcao == 4 && mapa->cells[linha][coluna + 1].type == TILE_PAREDE) {
        return false;
    }

    // Se chegou até aqui, significa que o local da escada é possível
    return true;
}
