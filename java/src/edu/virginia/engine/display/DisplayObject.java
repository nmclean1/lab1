package edu.virginia.engine.display;

import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;

import javax.imageio.ImageIO;

/**
 * A very basic display object for a java based gaming engine
 * 
 * */
public class DisplayObject {

	/* All DisplayObject have a unique id */
	private String id;

	/* The image that is displayed by this object */
	private BufferedImage displayImage;

	/* x,y position where object is drawn */
	private Point position;

	/* origin that object pivots off */
	private Point pivotPoint;

	/* radians of rotation of object */
	private int rotation;

	private Boolean visible;

	private float alpha;

	private float oldAlpha;

	private double scaleX;

	private double scaleY;

	private Shape hitbox;

	private DisplayObject parent;

	public boolean hasPhysics;

	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */
	public DisplayObject(String id) {
		this.setId(id);
		this.setPosition(new Point(0,0));
		this.setPivotPoint(new Point(0,0));
		this.setRotation(0);
		this.setPosition(new Point(0,0));
		this.setPivotPoint(new Point(0,0));
		this.setRotation(0);
		this.setVisible(true);
		this.setAlpha(1.0f);
		this.setOldAlpha(0.0f);
		this.setScaleX(1.0);
		this.setScaleY(1.0);
		this.initHitBox();
		this.hasPhysics = false;
	}

	public DisplayObject(String id, String fileName) {
		this.setId(id);
		this.setImage(fileName);
		this.setPosition(new Point(0,0));
		this.setPivotPoint(new Point(0,0));
		this.setRotation(0);
		this.setVisible(true);
		this.setAlpha(1.0f);
		this.setOldAlpha(0.0f);
		this.setScaleX(1.0);
		this.setScaleY(1.0);
		this.initHitBox();
		this.hasPhysics = false;
	}

	public void setVisible(Boolean visible) { this.visible = visible; }

	public Boolean getVisible() { return visible; }

	public void setAlpha(float alpha) { this.alpha = alpha; }

	public float getAlpha() { return alpha; }

	public void setOldAlpha(float oldAlpha) { this.oldAlpha = oldAlpha; }

	public float getOldAlpha() { return oldAlpha; }

	public void setScaleX(double scaleX) { this.scaleX = scaleX; }

	public double getScaleX() { return scaleX; }

	public void setScaleY(double scaleY) { this.scaleY = scaleY; }

	public double getScaleY() { return scaleY; }

	public void setScale(double scale) {this.setScaleX(scale); this.setScaleY(scale);}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setPosition(Point pos) { this.position = pos; }

	public Point getPosition() { return position; }

	public void setPivotPoint(Point pp) { this.pivotPoint = pp; }

	public Point getPivotPoint() { return pivotPoint; }

	public void setRotation(int rot) { this.rotation = rot; }

	public int getRotation() { return rotation; }

	public DisplayObject getParent() { return parent; }

	public Shape getHitbox() {
		return hitbox;
	}

	// Initialize the hitbox of the displayObject
	public void initHitBox() {
		Point pos = this.getPosition();
		Rectangle box = new Rectangle(pos.x,pos.y,this.getUnscaledWidth(),this.getUnscaledHeight());
		this.hitbox = box;
	}

	public void updateHitBoxRotate(int rot) {
		Point pp = this.getPivotPoint();
		Point pos = this.getPosition();
		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(rot),pp.x+pos.x,pp.y+pos.y);
		Shape newShape = tx.createTransformedShape(this.getHitbox());
		this.hitbox = newShape;
	}

	public void updateHitBox(int x, int y) {
		Point pos = this.getPosition();
		AffineTransform tx = new AffineTransform();
		tx.setToTranslation(x,y);
		Shape newShape = tx.createTransformedShape(this.getHitbox());
		this.hitbox = newShape;
	}

	public void updateHitBoxScale() {
		AffineTransform tx = new AffineTransform();
		Point pos = this.getPosition();
		int rot = this.getRotation();
		Point pp = this.getPivotPoint();
		Rectangle newrec = new Rectangle(pos.x,pos.y,(int)(this.getUnscaledWidth()*this.getScaleX()),(int)(this.getUnscaledHeight()*this.getScaleY()));
		tx.rotate(Math.toRadians(rot),pp.x+pos.x,pp.y+pos.y);
		Shape newShape = tx.createTransformedShape(newrec);
		this.hitbox = newShape;
		//System.out.println(pos.toString() +" " +this.getUnscaledWidth()*this.getScaleX() +"--" + newrec.toString());
	}

	/**
	 * Returns the unscaled width and height of this display object
	 * */
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		return displayImage.getWidth();
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		return displayImage.getHeight();
	}

	public BufferedImage getDisplayImage() {
		return this.displayImage;
	}

	protected void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}

	public void setPhysics(boolean phys) {
		this.hasPhysics = phys;
	}


	/**
	 * Helper function that simply reads an image from the given image name
	 * (looks in resources\\) and returns the bufferedimage for that filename
	 * */
	public BufferedImage readImage(String imageName) {
		BufferedImage image = null;
		try {
			String file = ("resources" + File.separator + imageName);
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
			e.printStackTrace();
		}
		return image;
	}

	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
	}


	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * */
	protected void update(ArrayList<Integer> pressedKeys) {
		
	}

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	public void draw(Graphics g) {
		
		if (displayImage != null) {
			
			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;
			applyTransformations(g2d);

			/* Actually draw the image, perform the pivot point translation here */
			g2d.drawImage(displayImage, 0, 0,
					(int) (getUnscaledWidth()),
					(int) (getUnscaledHeight()), null);

			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			reverseTransformations(g2d);
			//g2d.draw(this.getHitbox());
		}
	}

	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */
	protected void applyTransformations(Graphics2D g2d) {
		Point pp = this.getPivotPoint();

		g2d.translate(this.position.x, this.position.y);
		g2d.rotate(Math.toRadians(this.getRotation()),pp.x,pp.y);
		g2d.scale(this.scaleX, this.scaleY);
		float curAlpha;
		this.oldAlpha = curAlpha = ((AlphaComposite)
				g2d.getComposite()).getAlpha();
		g2d.setComposite(AlphaComposite.getInstance(3, curAlpha *
				this.alpha));
	}

	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */
	protected void reverseTransformations(Graphics2D g2d) {
		Point pp = this.getPivotPoint();

		g2d.setComposite(AlphaComposite.getInstance(3,
				this.oldAlpha));
		// scale back by the reciprocal (2x -> 1/2x)
		g2d.scale(1/(this.scaleX), 1/(this.scaleY));
		// rotate back around (20 radians -> -20 radians back)
		g2d.rotate(Math.toRadians((-1)*this.getRotation()),pp.x,pp.y);
		// translate back (+5 to the right, -5 to the left)
		g2d.translate((-1 * this.position.x),(-1 * this.position.y));
	}

}
