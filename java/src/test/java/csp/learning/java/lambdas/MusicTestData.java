package csp.learning.java.lambdas;

import csp.learning.java.lambdas.model.Album;
import csp.learning.java.lambdas.model.Artist;
import csp.learning.java.lambdas.model.Track;

class MusicTestData {

    static final Artist johnMayer = Artist.named("John Mayer")
                                          .from("Bridgeport")
                                          .with(Artist.named("John Mayer"));

    static final Artist fooFighters = Artist.named("Foo Fighters")
                                            .from("Seattle")
                                            .with(Artist.named("Dave Grohl"))
                                            .with(Artist.named("Nate Mendel"))
                                            .with(Artist.named("Pat Smear"))
                                            .with(Artist.named("Taylor Hawkins"))
                                            .with(Artist.named("Chris Shiflett"))
                                            .with(Artist.named("Rami Jaffee"));

    static final Artist theShins = Artist.named("The Shins")
                                         .from("Albuquerque")
                                         .with(Artist.named("James Mercer"))
                                         .with(Artist.named("Yuuki Matthews"))
                                         .with(Artist.named("Mark Watrous"))
                                         .with(Artist.named("Casey Foubert"))
                                         .with(Artist.named("John Sortland"))
                                         .with(Artist.named("Patti King"));

    static final Artist theTallestManOnEarth = Artist.named("The Tallest Man on Earth")
                                                     .from("Dalarna")
                                                     .with(Artist.named("Kristian Matsson"));

    static final Album oneTrackAlbum = Album.named("Wasting Light")
                                            .by(fooFighters)
                                            .with(Track.named("These Days"));

    static final Album fourTrackAlbum = Album.named("Continuum")
                                             .by(johnMayer)
                                             .with(Track.named("Belief"))
                                             .with(Track.named("Gravity"))
                                             .with(Track.named("Vultures"))
                                             .with(Track.named("In Repair"));

    static final Album zeroTrackAlbum = Album.named("Chutes Too Narrow")
                                             .by(theShins);

}
