package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Mateusz Brycki on 31/12/2016.
 */
public class AppController extends Controller{

    public Result generate() {

        return ok("Database is ready!");
    }
}
