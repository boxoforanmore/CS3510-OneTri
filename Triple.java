/*  a triple holds
  3 doubles, is
  immutable
  (DON'T change instance
   variables even though
   they're public!)
*/

import java.util.Scanner;

public class Triple {

  // instance variables:
  public double x, y, z;   // or?  double[] data --- use data[0], data[1], data[2]

  public Triple( double a, double b, double c ) {
    x=a; y=b; z=c;
  }

  public Triple( Scanner input ) {
    x = input.nextDouble();
    y = input.nextDouble();
    z = input.nextDouble();
  }

  // compute this . other
  public double dot( Triple other ) {
    return x*other.x + y*other.y + z*other.z;
  }

  // compute this X other
  public Triple cross( Triple other ) {
    return new Triple( y*other.z - z*other.y,
                       z*other.x - x*other.z,
                       x*other.y - y*other.x );
  }

  // compute this - other
  public Triple minus( Triple other ) {
    return new Triple( x-other.x, y-other.y, z - other.z );
  }

  // compute this + other
  public Triple plus( Triple other ) {
    return new Triple( x+other.x, y+other.y, z+other.z );
  }

  // compute this + [a,b,c]
  public Triple plus( double a, double b, double c ) {
    return new Triple( x+a, y+b, z+c );
  }

  // return this scalar multiplied by s
  public Triple mult( double s ) {
    return new Triple( s*x, s*y, s*z );
  }

  // return this normalized
  public Triple normalized() {
    double f = 1/Math.sqrt( this.dot(this) );
    return new Triple( f*x, f*y, f*z );
  }

  public String toString() {
    return "[" + x  + "," + y + "," + z + "]";
  }

  public static Triple xAxis = new Triple(1,0,0);
  public static Triple yAxis = new Triple(0,1,0);
  public static Triple zAxis = new Triple(0,0,1);

  public static Triple transform( Triple p, Triple e,
    Triple eMinusA, Triple bMinusA, Triple cMinusA,
    double len2ea, double len2ba, double len2ca ) {

    Triple pMinusE = p.minus(e);
    double lambda = - len2ea / eMinusA.dot( pMinusE );
System.out.println("lambda = " + lambda + " len2ba: " + len2ba );

    return new Triple( lambda*bMinusA.dot(pMinusE) / len2ba, // beta
                       lambda*cMinusA.dot(pMinusE) / len2ca, // gamma
                       2*lambda-1 ); // depth info
  }

  public static void main(String[] args) {
    Triple v = new Triple( 1, 2, 3 );
    Triple w = new Triple( -3, 1, 5 );
    Triple u = v.cross( w );
    System.out.println( v.dot(u) + " " + w.dot(u) );
  }


}
