/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usman
 */
public class internet {
    
       //private String final_q2;
       internet (String final_query){
     
        Run(final_query);

    
       }

    //call make_search_query and then save the string to call run

    
    private void Run(String query){

        //http://mint.bio.uniroma2.it//mint/search/search.do?queryType=protein&ac=P46777


        if( !java.awt.Desktop.isDesktopSupported() ) {

            System.err.println( "Desktop is not supported (fatal)" );
            System.exit( 1 );
        }

        if ( query.length() == 0 ) {

            System.out.println( "No Query" );
            System.exit( 0 );
        }

        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

        if( !desktop.isSupported( java.awt.Desktop.Action.BROWSE ) ) {

            System.err.println( "Desktop doesn't support the browse action (fatal)" );
            System.exit( 1 );
        }


            try {

                java.net.URI uri = new java.net.URI(query);
                
                desktop.browse( uri );
                
            }
            catch ( Exception e ) {

                System.err.println( e.getMessage() );
            }

    }
}
