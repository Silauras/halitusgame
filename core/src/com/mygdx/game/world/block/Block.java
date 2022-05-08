package com.mygdx.game.world.block;

import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;

public class Block {

    public Block(Integer id, Texture texture) {
        this.id = id;
        this.texture = texture;
    }

    private Integer id;
    private Texture texture;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Block)) return false;

        Block block = (Block) o;

        if (!id.equals(block.id)) return false;
        return Objects.equals(texture, block.texture);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (texture != null ? texture.hashCode() : 0);
        return result;
    }
}
