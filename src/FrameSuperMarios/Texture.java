package FrameSuperMarios;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Texture {

	private final String PARENT_FOLDER = "/tile";
	
	private final int mario_l_count  = 21;
	private final int mario_s_count = 14;
	
	private final int mario_lf_count  = 21;
	
	private final int tile_1_count = 28;
	private final int tile_2_count = 33;
	private final int block_1_count = 21;
	
	private BufferedImageLoader loader;
	
	private BufferedImage player_sheet, enemy_sheet, npc_sheet, block_sheet, tile_sheet, game_over_sheet, intro_sheet,castle_sheet;

	BufferedImage[] coin_1;
	public BufferedImage[] mario_l, mario_s, tile_1, tile_2, tile_3, tile_4, pipe_1,castle_1,enemy_1,brick_1,question_1,mushroom_1,fire_1,mario_f,fire_bomb;

	private BufferedImage brickAnimation;
	
	public Texture() {
		mario_l = new BufferedImage[mario_l_count];
		mario_s = new BufferedImage[mario_s_count];
		mario_f = new BufferedImage[mario_lf_count];
		brick_1 = new BufferedImage[4];
		tile_1 = new BufferedImage[tile_1_count + tile_2_count];
		tile_2 = new BufferedImage[tile_1_count + tile_2_count];
		tile_3 = new BufferedImage[tile_1_count + tile_2_count];
		tile_4 = new BufferedImage[tile_1_count + tile_2_count];
		castle_1 = new BufferedImage[1];
		pipe_1 = new BufferedImage[5];
		enemy_1 = new BufferedImage[28];
		question_1 = new BufferedImage[1];
		mushroom_1 = new BufferedImage[1];
		fire_bomb = new BufferedImage[1];
		fire_1 = new BufferedImage[1];
		coin_1 = new BufferedImage[1];
		loader = new BufferedImageLoader();
	
		try {
			player_sheet = loader.loadImage(PARENT_FOLDER+"/NES - Super Mario Bros - Mario & Luigi.png");
			enemy_sheet = loader.loadImage(PARENT_FOLDER+"/NES - Super Mario Bros - Enemies & Bosses.png");
			npc_sheet = loader.loadImage(PARENT_FOLDER+"/NES - Super Mario Bros - Items Objects and NPCs.png");
			block_sheet = loader.loadImage(PARENT_FOLDER+"/NES - Super Mario Bros - Item and Brick Blocks.png");
			tile_sheet = loader.loadImage(PARENT_FOLDER+"/NES - Super Mario Bros - Tileset.png");
			intro_sheet = loader.loadImage(PARENT_FOLDER+"/NES - Super Mario Bros - Title Screen HUD and Miscellaneous.png");
			castle_sheet = loader.loadImage(PARENT_FOLDER+"/castle.png");
			brickAnimation = loader.loadImage(PARENT_FOLDER+"/brick-animation.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getPlayerTextures();
		getTileTextures();
		GetPipeTextures();
		getCastleTextures();
		getEnemyTextures();
		getbrickTextures();
		getquestionTextures();
		getmushroomTextures();
		getfireTextures();
		getmariofTextures();
		getfirebombTextures();
		getCoinTextures();
	}
	
	   
	private void getfirebombTextures() {
		int x_off = 115;
		int y_off = 148;
		int width = 16; 
		int height = 16;
		
		
            fire_bomb[0] = npc_sheet.getSubimage(x_off , y_off , width, height);
        
	}
	
	private void getCoinTextures() {
		int x_off = 2;
		int y_off = 82;
		int width = 16; 
		int height = 16;
        coin_1[0] = npc_sheet.getSubimage(x_off , y_off , width, height);
        
	}
	private void getCastleTextures() {
		int x_off = 0;
		int y_off = 0;
		int width = 250;
		int height = 250;
		castle_1[0] = castle_sheet.getSubimage(x_off,y_off,width,height);
		
	}
	
	private void getEnemyTextures() { 
		int x_off = 0;
		int y_off = 16;
		int width = 16;
		int height = 16;
		for (int j =0; j<28; j++) {
		enemy_1[j] = enemy_sheet.getSubimage(x_off + j*(width),y_off,width,height);
		}
		
	}
	
	private void getbrickTextures() {
		for(int i = 0; i < 4; i++){
            brick_1[i] = brickAnimation.getSubimage(i*105, 0, 105, 105);
        }
	}
	
	private void getPlayerTextures() {
		int x_off = 80;
		int y_off = 1;
		int width = 16;
		int height = 32;
		
		for (int i = 0; i < mario_l_count; i++) {
			mario_l[i] = player_sheet.getSubimage(x_off + i*(width+1), y_off, width, height);
		}
		y_off += height + 1;
		height = 16;
		
		for (int i = 0; i < mario_s_count; i++) {
			mario_s[i] = player_sheet.getSubimage(x_off + i*(width+1), y_off, width, height);
		}
	}
	
	private void getmariofTextures() {
		int x_off = 80;
		int y_off = 129;
		int width = 16;
		int height = 32;
		
		for (int i = 0; i < mario_l_count; i++) {
			mario_f[i] = player_sheet.getSubimage(x_off + i*(width+1), y_off, width, height);
		}
	}
	
	private void getTileTextures() { 
		int x_off = 0;
		int y_off = 0;
		int width = 16; 
		int height = 16;
		
		for (int j =0; j<4; j++) {
			for(int i = 0; i < tile_1_count; i++) {
				if (j ==0) {
					tile_1[i] = tile_sheet.getSubimage(x_off + i*width, y_off + j*height*2, width, height);
				}
				else if (j == 1) {
					tile_2[i] = tile_sheet.getSubimage(x_off + i*width, y_off + j*height*2, width, height);
				}
				else if (j == 2) {
					tile_3[i] = tile_sheet.getSubimage(x_off + i*width, y_off + j*height*2, width, height);
				}
				else if (j == 3) {
					tile_4[i] = tile_sheet.getSubimage(x_off + i*width, y_off + j*height*2, width, height);
				}
			}
			y_off += height;
			
				for(int i = 0; i < tile_1_count; i++) {
					if (j ==0) {
						tile_1[i + tile_1_count] = tile_sheet.getSubimage(x_off + i*width, y_off + j*height*2, width, height);
					}
					else if (j == 1) {
						tile_2[i + tile_1_count] = tile_sheet.getSubimage(x_off + i*width, y_off + j*height*2, width, height);
					}
					else if (j == 2) {
						tile_3[i + tile_1_count] = tile_sheet.getSubimage(x_off + i*width, y_off + j*height*2, width, height);
					}
					else if (j == 3) {
						tile_4[i + tile_1_count] = tile_sheet.getSubimage(x_off + i*width, y_off + j*height*2, width, height);
					}
				}
		}
	}
	
	private void getquestionTextures() {
		int x_off = 432;
		int y_off = 0;
		int width = 16; 
		int height = 16;
		
		
            question_1[0] = tile_sheet.getSubimage(x_off , y_off , width, height);
        
	}
	
	private void getfireTextures() {
		int x_off = 32;
		int y_off = 32;
		int width = 16; 
		int height = 16;
		
		
            fire_1[0] = npc_sheet.getSubimage(x_off , y_off , width, height);
        
	}
	
	private void getmushroomTextures() {
		int x_off = 0;
		int y_off = 0;
		int width = 16; 
		int height = 16;
		
		
            mushroom_1[0] = npc_sheet.getSubimage(x_off , y_off , width, height);
        
	}
	
	
	
	private void GetPipeTextures() { 
		int x_off = 0;
		int y_off = 16*8;
		int width = 32; 
		int height = 32;
		
		pipe_1[0] = tile_sheet.getSubimage(x_off,y_off,width,height);
		pipe_1[1] = tile_sheet.getSubimage(x_off,y_off + height,width,height);
		pipe_1[2] = tile_sheet.getSubimage(x_off + width,y_off,width,height);
		pipe_1[3] = tile_sheet.getSubimage(x_off + width,y_off +height,width,height);
		x_off = 0;
		y_off = 144;
		width = 32; 
		height = 16;
		pipe_1[4] = tile_sheet.getSubimage(x_off,y_off,width,height);
		
	}
	public BufferedImage[] getMariol() {
		return mario_l;
	}
	public BufferedImage[] getMarios() {
		return mario_s;
	}
	public BufferedImage[] gettile_1() {
		return tile_1;
	}
	public BufferedImage[] gettile_2() {
		return tile_2;
	}
	public BufferedImage[] gettile_3() {
		return tile_3;
	}
	public BufferedImage[] gettile_4() {
		return tile_4;
	}
	public BufferedImage[] getpipe_1() {
		return pipe_1;
	}
	public BufferedImage[] getcastle_1() {
		return castle_1;
	}
	public BufferedImage[] getenemy_1() {
		return enemy_1;
	}
	public BufferedImage[] getbrick_1() {
		return brick_1;
	}
	public BufferedImage[] question_1() {
		return question_1;
	}
	public BufferedImage[] mushroom_1() {
		return mushroom_1;
	}
	public BufferedImage[] fire_1() {
		return fire_1;
	}
	
	public BufferedImage[] mario_f() {
		return mario_f;
	}
	public BufferedImage[] fire_bomb() {
		return fire_bomb;
	}
	public BufferedImage[] coin_1() {
		return coin_1;
	}
}
