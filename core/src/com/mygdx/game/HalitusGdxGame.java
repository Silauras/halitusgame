package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.world.World;
import com.mygdx.game.world.block.Block;

public class HalitusGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    World world;
    OrthographicCamera camera = new OrthographicCamera();
    Block air = new Block(0, null);
    Float zoom = 1f;
    Float cameraSpeed = 2f;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("ukraine_logo.png");
        world = new World();
        Block basicBlock = new Block(1, new Texture("basic_block.png"));
        Block basicTable = new Block(2, new Texture("basic_table.png"));
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                for (int k = 0; k < 1024; k++) {
                    world.getVoxels()[i][j][k] = air;
                }
            }
        }
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                world.getVoxels()[i][j][512] = basicBlock;
                world.getVoxels()[i][j][511] = basicBlock;
            }
        }
        world.getVoxels()[1][1][512] = air;
        world.getVoxels()[0][1][513] = basicTable;
        camera.position.set(256, 256, 515);
    }

    @Override
    public void render() {

        getUserInput();

        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                for (int k = 511; k < 514; k++) {
                    Block block = world.getVoxels()[i][j][k];
                    Texture texture = block.getTexture();
                    if (!block.equals(air)) {
                        batch.draw(texture,
                                camera.position.x * 0.894427191f - camera.position.y * 2.2360679775f - (j - i) * 32 * zoom,
                                camera.position.y * 1.11803398875f + camera.position.x * 0.4472135955f - ((i + j) * 16 - (k - 512) * 32) * zoom,
                                texture.getWidth() * zoom,
                                texture.getHeight() * zoom);
                    }
                }
            }
        }
        batch.end();
    }

    private void getUserInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.position.set(
                    camera.position.x,
                    camera.position.y - 1 / zoom * cameraSpeed,
                    camera.position.y
            );
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.position.set(
                    camera.position.x,
                    camera.position.y + 1 / zoom * cameraSpeed,
                    camera.position.y
            );
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.position.set(
                    camera.position.x - 1 / zoom * cameraSpeed * 2,
                    camera.position.y,
                    camera.position.y
            );
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.position.set(
                    camera.position.x + 1 / zoom * cameraSpeed * 2,
                    camera.position.y,
                    camera.position.y
            );
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
