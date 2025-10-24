public class Color {
    private int red, green, blue;

    // Constructor
    public Color(int r, int g, int b) {
        red = r;
        green = g;
        blue = b;
    }

    // Getters
    public int getRed()   { return red; }
    public int getGreen() { return green; }
    public int getBlue()  { return blue; }

    // Combine RGB into one int
    public int getRGB() {
        return (red << 16) | (green << 8) | blue;
    }

    // Static: create from hex string
    public static Color decode(String hex) {
        // Remove 0x or # if present
        if (hex.startsWith("0x") || hex.startsWith("0X")) { //startsWith daje true ili false ako pocinje sa navedenim ili ne
            hex = hex.substring(2);
        } else if (hex.startsWith("#")) {
            hex = hex.substring(1);
        }
        int value = Integer.parseInt(hex, 16);    //pretvara string u int na bazi navedenoj kao drugi argument
        int r = (value >> 16) & 0xFF;
        int g = (value >> 8) & 0xFF;
        int b = value & 0xFF;
        return new Color(r, g, b);
    }

    // Static: convert RGB to HSB (simplified version)
    public static float[] RGBtoHSB(int r, int g, int b, float[] hsb) {
        if (hsb == null) hsb = new float[3];
        float rf = r / 255f;
        float gf = g / 255f;
        float bf = b / 255f;
        System.out.println("bf:" + bf);

        float max = Math.max(rf, Math.max(gf, bf));  //uzima najvece od zadanih vrijednosti
        float min = Math.min(rf, Math.min(gf, bf));
        float delta = max - min;

        float hue;
        if (delta == 0) hue = 0;
        else if (max == rf) hue = ((gf - bf) / delta) % 6;
        else if (max == gf) hue = ((bf - rf) / delta) + 2;
        else hue = ((rf - gf) / delta) + 4;
        hue /= 6;
        if (hue < 0) hue += 1;

        float sat = (max == 0) ? 0 : delta / max;
        float bri = max;

        hsb[0] = hue;
        hsb[1] = sat;
        hsb[2] = bri;
        return hsb;
    }
    
        public static float[] RGBtoHSL(int r, int g, int b, float[] hsl) {
        if (hsl == null) hsl = new float[3];
        float rf = r / 255f;
        float gf = g / 255f;
        float bf = b / 255f;
        System.out.println("bf:" + bf);

        float max = Math.max(rf, Math.max(gf, bf));  //uzima najvece od zadanih vrijednosti
        float min = Math.min(rf, Math.min(gf, bf));
        float delta = max - min;

        float hue;
        if (delta == 0) hue = 0;
        else if (max == rf) hue = ((gf - bf) / delta) % 6;
        else if (max == gf) hue = ((bf - rf) / delta) + 2;
        else hue = ((rf - gf) / delta) + 4;
        hue /= 6;
        if (hue < 0) hue += 1;

        float sat = (max == 0) ? 0 : delta / max;
        float bri = (max+min)/2;

        hsl[0] = hue;
        hsl[1] = sat;
        hsl[2] = bri;
        return hsl;
    }
    
    public static float[] RGBtoCMYK(int r, int g, int b, float[] cmyk) {
    if (cmyk == null) cmyk = new float[4];
    float rf = r / 255f;
    float gf = g / 255f;
    float bf = b / 255f;

    float k = 1 - Math.max(rf, Math.max(gf, bf));
    float c = 0, m = 0, y = 0;

    if (k < 1) { // avoid division by zero
        c = (1 - rf - k) / (1 - k);
        m = (1 - gf - k) / (1 - k);
        y = (1 - bf - k) / (1 - k);
    }

    cmyk[0] = c;
    cmyk[1] = m;
    cmyk[2] = y;
    cmyk[3] = k;
    return cmyk;
}

}
