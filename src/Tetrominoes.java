/**
 * Types of all tetrominoes(blocks)
 * 
 */
/**
 * 
 * +-+       +-+    +-+        +-+  
 * | |       | |    | |        | | 
 * +-+-+   +-+-+    +-+      +-+-+-+ 
 * | | |   | | |    | |      | | | |
 * +-+-+   +-+-+    +-+      +-+-+-+
 *   | |   | |      | |    
 *   +-+   +-+      +-+
 *                  | |
 *                  +-+
 *  Z        S        Line       T
 * (0,-1)  (0,-1)  (0,-1)    (-1,0)
 * (0,0)   (0,0)   (0,0)     (0,0)
 * (-1,0)  (1,0)   (0,1)     (1,0)
 * (-1,1)  (1,1)   (0,2)     (0,1)
 *                   
 *  +-+-+     +-+     +-+  
 *  | | |     | |     | | 
 *  +-+-+     +-+     +-+
 *  | | |     | | |   | |
 *  +-+-+   +-+-+     +-+-+
 *          | | |     | | |    
 *          +-+-+     +-+-+
 *                   
 *  Square  L         MirroredL
 *  (0,0)   (-1,-1)   (1,-1)
 *  (1,0)   (0,-1)    (0,-1)
 *  (0,1)   (0,0)     (0,0)
 *  (1,1)   (0,1)     (0,1)
 *              
 */

public enum Tetrominoes { 
	NoShape,      // Empty
	ZShape,       // S
	SShape,       // Z
	LineShape,    // Line
    TShape,       // VertialBar
    SquareShape,  // Square
    LShape,       // ReverseL
    MirroredLShape  // L
};

