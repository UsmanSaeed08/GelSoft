/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usman
 */
public class ppi_MINT {
    
    ppi_MINT(String final_query){
        Run(make_search_query(final_query));

    }
    private String final_q2;

    //call make_search_query and then save the string to call run

    public String make_search_query (String final_query){
        // final_query would have the protein accession

        String q1 = "http://mint.bio.uniroma2.it//mint/search/search.do?queryType=protein&ac=";
        String q2 = "http://mint.bio.uniroma2.it//mint/search/search.do?queryType=interaction&ac=";

        final_query = q1 + final_query;
        final_q2 = q2 + final_query;

        return final_query;
    }
    public void Run(String query){

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
                java.net.URI uri2 = new java.net.URI(final_q2);
                desktop.browse( uri );
                desktop.browse( uri2 );
            }
            catch ( Exception e ) {

                System.err.println( e.getMessage() );
            }

    }
}
