package therent.util;

public class JDBCUtil {
    //url de la base de datos, muy largo para administrarse de otra manera
<<<<<<< HEAD
    private static final String DATABASE_URI="jdbc:mysql://ujcrxuvtknjzvqqc:AK5SjLumAyflpDnq0Knk@bxhla1vg2uaypvnsiqyx-mysql.services.clever-cloud.com:3306/bxhla1vg2uaypvnsiqyx";

=======
   // private static final String DATABASE_URI="jdbc:mysql://uuvywdmg2p2x5tad:g8jfywm9ucvrvXfQl7Xe@bemorsa1qiar4u96lent-mysql.services.clever-cloud.com:3306/bemorsa1qiar4u96lent";
      private static  final String DATABASE_URI = "jdbc:mysql://ujcrxuvtknjzvqqc:AK5SjLumAyflpDnq0Knk@bxhla1vg2uaypvnsiqyx-mysql.services.clever-cloud.com:3306/bxhla1vg2uaypvnsiqyx";
>>>>>>> f3b2e0048b558f343450e8bcf2637b593bac4b2a
    public static String getDatabaseUri(){
        return DATABASE_URI;
    }

}
