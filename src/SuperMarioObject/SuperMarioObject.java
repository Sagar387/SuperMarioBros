package SuperMarioObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import SuperMarioObjectUtil.ObjectID;

public abstract class SuperMarioObject {
private float x;
private float y;
private ObjectID id;
private float velX;
private float velY;
private float width;
private float height;
private int scale;

public SuperMarioObject(float x, float y ,ObjectID id ,float width,float height, int scale) {
	this.x = x * scale;
	this.y = y * scale;
	this.id = id;
	this.width = width * scale;
	this.height = height * scale;
	this.scale = scale;
}
public abstract void tick();
public abstract void render (Graphics g);
public abstract Rectangle getBounds();

public void applyGravity() {
	velY += 0.5f;
}
public void move() {
	velX = 0.9f;
}
public void setX(float x) {
	this.x=x;
}
public void setY (float y) {
	this.y=y;
}
public void setID (Object ID) {
	this.id=id;
}
public void setVelX(float velX) {
	this.velX = velX;
}

public void setVelY(float velY) {
	this.velY=velY;
}
public void setHeight (float height) {
	this.height = height * scale;
}
public float getX() {
	return x;
}
public float getY() {
	return y;
}
public ObjectID getID() {
	return id;
}
public float getvelX() {
	return velX;
}
public float getvelY() {
	return velY;
}
public float getwidth() {
	return width;
}
public float getheight() {
	return height;
}
public abstract boolean shouldBeRemoved();
public abstract boolean add();
}
