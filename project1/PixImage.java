/* PixImage.java */

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 *
 *  All methods in this class must be implemented to complete Part I.
 *  See the README file accompanying this project for additional details.
 */

public class PixImage {

  /**
   *  Define any variables associated with a PixImage object here.  These
   *  variables MUST be private.
   */
   
   private int w;
   private int h;
   private Color[][] p;
   
   private class Color {
	public short red;
	public short green;
	public short blue;
	
	public Color() {
		red = 0;
		green = 0;
		blue = 0;
	}
	
	public Color(short red, short green, short blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	}

  /**
   * PixImage() constructs an empty PixImage with a specified width and height.
   * Every pixel has red, green, and blue intensities of zero (solid black).
   *
   * @param width the width of the image.
   * @param height the height of the image.
   */
  public PixImage(int width, int height) {
    // Your solution here.
	w = width;
	h = height;
	p = new Color[w][h];
	for(int i = 0; i < w; i++) {
		for(int j = 0; j < h; j++) {
			p[i][j] = new Color();
        }
    }
  }

  /**
   * getWidth() returns the width of the image.
   *
   * @return the width of the image.
   */
  public int getWidth() {
    // Replace the following line with your solution.
    return w;
  }

  /**
   * getHeight() returns the height of the image.
   *
   * @return the height of the image.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return h;
  }

  /**
   * getRed() returns the red intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the red intensity of the pixel at coordinate (x, y).
   */
  public short getRed(int x, int y) {
    // Replace the following line with your solution.
    return p[x][y].red;
  }

  /**
   * getGreen() returns the green intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the green intensity of the pixel at coordinate (x, y).
   */
  public short getGreen(int x, int y) {
    // Replace the following line with your solution.
    return p[x][y].green;
  }

  /**
   * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the blue intensity of the pixel at coordinate (x, y).
   */
  public short getBlue(int x, int y) {
    // Replace the following line with your solution.
    return p[x][y].blue;
  }

  /**
   * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
   * and blue intensities.
   *
   * If any of the three color intensities is NOT in the range 0...255, then
   * this method does NOT change any of the pixel intensities.
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @param red the new red intensity for the pixel at coordinate (x, y).
   * @param green the new green intensity for the pixel at coordinate (x, y).
   * @param blue the new blue intensity for the pixel at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
    // Your solution here.
	if( red < 0 && red > 255 && green < 0 && green > 255 && blue < 0 && green > 255) 
		return;
	else {
		p[x][y].red = red;
		p[x][y].green = green;
		p[x][y].blue = blue;
	}
  }

  /**
   * toString() returns a String representation of this PixImage.
   *
   * This method isn't required, but it should be very useful to you when
   * you're debugging your code.  It's up to you how you represent a PixImage
   * as a String.
   *
   * @return a String representation of this PixImage.
   */
  public String toString() {
    // Replace the following line with your solution.
	String result;
	result = "";
	for( int j = 0; j < h; j++) {
		result = result + "|";
		for( int i = 0; i < w; i++) 
			result = result + " " + getRed(i,j) + " " + getGreen(i,j) + " " + getBlue(i,j) + " |";
		result = result + "\n";
	}
    return result;
  }
  /**
   * boxBlur() returns a blurred version of "this" PixImage.
   *
   * If numIterations == 1, each pixel in the output PixImage is assigned
   * a value equal to the average of its neighboring pixels in "this" PixImage,
   * INCLUDING the pixel itself.
   *
   * A pixel not on the image boundary has nine neighbors--the pixel itself and
   * the eight pixels surrounding it.  A pixel on the boundary has six
   * neighbors if it is not a corner pixel; only four neighbors if it is
   * a corner pixel.  The average of the neighbors is the sum of all the
   * neighbor pixel values (including the pixel itself) divided by the number
   * of neighbors, with non-integer quotients rounded toward zero (as Java does
   * naturally when you divide two integers).
   *
   * Each color (red, green, blue) is blurred separately.  The red input should
   * have NO effect on the green or blue outputs, etc.
   *
   * The parameter numIterations specifies a number of repeated iterations of
   * box blurring to perform.  If numIterations is zero or negative, "this"
   * PixImage is returned (not a copy).  If numIterations is positive, the
   * return value is a newly constructed PixImage.
   *
   * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
   * appear in the new, output PixImage only.
   *
   * @param numIterations the number of iterations of box blurring.
   * @return a blurred version of "this" PixImage.
   */
    public PixImage boxBlur(int numIterations) {
    // Replace the following line with your solution.
		PixImage prev = this;
		short newred;
		short newgreen;
		short newblue;
		while( numIterations > 0 ) {
			PixImage copy = new PixImage(w,h);
			for( int i = 0; i < w; i++ ) 
				for( int j = 0; j < h; j++ ) {
					copy.setPixel(i,j,prev.getRed(i,j),prev.getGreen(i,j),prev.getBlue(i,j));
				} 
			for( int j = 0; j < h; j++)
				for( int i = 0; i < w; i++ ) {
					int sumred = 0;
					int sumgreen = 0;
					int sumblue = 0;
					short[][] neighborsPixel = prev.getNeighbours(i,j);
					for( int x = 0; x < neighborsPixel.length; x++ ) {
						sumred = sumred +  neighborsPixel[x][0];
						sumgreen = sumgreen + neighborsPixel[x][1];
						sumblue = sumblue + neighborsPixel[x][2];
					}
					
					newred = (short) (sumred / neighborsPixel.length);
					newgreen = (short) (sumgreen / neighborsPixel.length);
					newblue = (short) (sumblue / neighborsPixel.length);
					copy.setPixel(i,j,newred,newgreen,newblue);
				}
			numIterations--;
			prev = copy;
		}
		return prev;
    }
  
  // Using 2D Array to handle neighbours and their RGB.
  public short[][] getNeighbours(int x, int y) {
	short[][] neighborsPixel = new short[9][3];
	int count = 0;
	for(int j = -1; j <= 1; j++) 
		if( (y + j) >= 0 && (y + j) < h ) {
			for(int i = -1; i <= 1; i++) {
				if( (x + i) >= 0 && (x + i) < w ) {
					neighborsPixel[count][0] = p[x+i][y+j].red;
					neighborsPixel[count][1] = p[x+i][y+j].green;
					neighborsPixel[count][2] = p[x+i][y+j].blue;
					count++;
				}
			}
		}
	short[][] finalPixel = new short[count][3];
	for(int i = 0; i < count; i++) {
		finalPixel[i][0] = neighborsPixel[i][0];
		finalPixel[i][1] = neighborsPixel[i][1];
		finalPixel[i][2] = neighborsPixel[i][2];
	}
	return finalPixel;
	}
  /**
   * mag2gray() maps an energy (squared vector magnitude) in the range
   * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
   * is logarithmic, but shifted so that values of 5,080 and below map to zero.
   *
   * DO NOT CHANGE THIS METHOD.  If you do, you will not be able to get the
   * correct images and pass the autograder.
   *
   * @param mag the energy (squared vector magnitude) of the pixel whose
   * intensity we want to compute.
   * @return the intensity of the output pixel.
   */
  private static short mag2gray(long mag) {
    short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

    // Make sure the returned intensity is in the range 0...255, regardless of
    // the input value.
    if (intensity < 0) {
      intensity = 0;
    } else if (intensity > 255) {
      intensity = 255;
    }
    return intensity;
  }

  /**
   * sobelEdges() applies the Sobel operator, identifying edges in "this"
   * image.  The Sobel operator computes a magnitude that represents how
   * strong the edge is.  We compute separate gradients for the red, blue, and
   * green components at each pixel, then sum the squares of the three
   * gradients at each pixel.  We convert the squared magnitude at each pixel
   * into a grayscale pixel intensity in the range 0...255 with the logarithmic
   * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
   * pixel intensities reflect the strength of the edges.
   *
   * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
   *
   * @return a grayscale PixImage representing the edges of the input image.
   * Whiter pixels represent stronger edges.
   */
  public PixImage sobelEdges() {
    // Replace the following line with your solution.
	PixImage copy = new PixImage(w,h);
	int[] indexX = {1,2,1,0,0,0,-1,-2,-1};
	int[] indexY = {1,0,-1,2,0,-2,1,0,-1};
	for(int i = 0; i < w; i++)
		for(int j = 0; j < h; j++) {
			long gxred = 0, gyred = 0, gxgreen = 0, gygreen = 0, gxblue = 0, gyblue = 0;
		    long energy;
		    int count = 0;
			for(int x = i - 1; x <= i + 1; x++) 
				for( int y = j - 1; y <= j + 1; y++ ) {
					long[] pixel = new long[3];
					pixel = reflection(x,y);	
					gxred = gxred + indexX[count] * pixel[0];
					gxgreen = gxgreen + indexX[count] * pixel[1];
					gxblue = gxblue + indexX[count] * pixel[2];
					gyred = gyred + indexY[count] * pixel[0];
					gygreen = gygreen + indexY[count] * pixel[1];
					gyblue = gyblue + indexY[count] * pixel[2];
					count++;
				}
			energy = (long)(Math.pow(gxred,2) + Math.pow(gyred,2) + Math.pow(gxgreen,2) + Math.pow(gygreen,2) + Math.pow(gxblue,2) + Math.pow(gyblue,2));
			copy.setPixel(i,j,mag2gray(energy),mag2gray(energy),mag2gray(energy));
		}
	return copy;
  } 
    // Don't forget to use the method mag2gray() above to convert energies to
    // pixel intensities.
	//Using 1D array to handle reflection.
    public long[] reflection(int x, int y){
		long[] getPixel = new long[3];
		if(x < 0) x++;
		if(x > w-1) x--;
		if(y < 0) y++;
		if(y > h-1) y--;
		getPixel[0] = getRed(x,y);
		getPixel[1] = getGreen(x,y);
		getPixel[2] = getBlue(x,y);
		return getPixel;
	}
	

  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * equals() checks whether two images are the same, i.e. have the same
   * dimensions and pixels.
   *
   * @param image a PixImage to compare with "this" PixImage.
   * @return true if the specified PixImage is identical to "this" PixImage.
   */
  public boolean equals(PixImage image) {
    int width = getWidth();
    int height = getHeight();

    if (image == null ||
        width != image.getWidth() || height != image.getHeight()) {
      return false;
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (! (getRed(x, y) == image.getRed(x, y) &&
               getGreen(x, y) == image.getGreen(x, y) &&
               getBlue(x, y) == image.getBlue(x, y))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * main() runs a series of tests to ensure that the convolutions (box blur
   * and Sobel) are correct.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                                                   { 30, 120, 250 },
                                                   { 80, 250, 255 } });
    System.out.println("Testing getWidth/getHeight on a 3x3 image.  " +
                       "Input image:");
    System.out.print(image1);
    doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 3x3 image.");
    doTest(image1.boxBlur(1).equals(
           array2PixImage(new int[][] { { 40, 108, 155 },
                                        { 81, 137, 187 },
                                        { 120, 164, 218 } })),
           "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));
    doTest(image1.boxBlur(2).equals(
           array2PixImage(new int[][] { { 91, 118, 146 },
                                        { 108, 134, 161 },
                                        { 125, 151, 176 } })),
           "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));
    doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
           "Incorrect box blur (1 rep + 1 rep):\n" +
           image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

    System.out.println("Testing edge detection on a 3x3 image.");
    doTest(image1.sobelEdges().equals(
           array2PixImage(new int[][] { { 104, 189, 180 },
                                        { 160, 193, 157 },
                                        { 166, 178, 96 } })),
           "Incorrect Sobel:\n" + image1.sobelEdges());


    PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
                                                   { 0, 0, 100 } });
    System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                       "Input image:");
    System.out.print(image2);
    doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 2x3 image.");
    doTest(image2.boxBlur(1).equals(
           array2PixImage(new int[][] { { 25, 50, 75 },
                                        { 25, 50, 75 } })),
           "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));

    System.out.println("Testing edge detection on a 2x3 image.");
    doTest(image2.sobelEdges().equals(
           array2PixImage(new int[][] { { 122, 143, 74 },
                                        { 74, 143, 122 } })),
           "Incorrect Sobel:\n" + image2.sobelEdges());
  }
}
