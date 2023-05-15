#ifndef MAPA_H
#define MAPA_H

#define MAX_LARGURA 80
#define MAX_ALTURA 24

typedef enum {
    TILE_DEFAULT,
    TILE_PAREDE,
    TILE_ESCADA
} TileType;

typedef struct {
    char tile;
    int walkable;
} MapTileDefault;

typedef struct {
    char tile;
    int climbable;
} MapTileParede;

typedef struct {
    char tile;
    int direcao; // 1 = cima, 2 = baixo, 3 = esquerda, 4 = direita
} MapTileEscada;

typedef union {
    MapTileDefault default_tile;
    MapTileParede parede_tile;
    MapTileEscada escada_tile;
} MapTile;

typedef struct {
    TileType type;
    MapTile tile;
} MapCell;

typedef struct {
    MapCell cells[MAX_ALTURA][MAX_LARGURA];
} Mapa;

void gerar_mapa(Mapa *mapa);
void desenha_mapa(Mapa *mapa, Personagem *personagem);

#endif
