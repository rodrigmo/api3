package api3

import api3.LogService
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import org.grails.web.json.JSONObject

import java.time.LocalDate

class CidadeController {

    static responseFormats = ["json"]
    static defaultAction = "get"
    static allowedMethods = [
            save: "POST",
            list: "GET",
            update: "PUT",
            delete: "DELETE",
            get: "GET"
    ]


    def list() {

//        Map retornoProvisorio = [:]
//        retornoProvisorio.status = "200"
//        retornoProvisorio.text = "Ok"

//        RestBuilder rest = new RestBuilder(connectTimeout: 10000, readTimeout: 100000, proxy: null)
        RestBuilder rest = new RestBuilder()
        LogService logService = new LogService()

        String baseURL = "http://localhost:8080/api2/cidade/"

        String finalURL = baseURL+"list/"
        RestResponse resReq = rest.get(finalURL)
        JSONObject responseJson = resReq.json as JSONObject

        logService.salvarLog(request, responseJson)
        respond(responseJson, status: resReq.status)

//        respond(retornoProvisorio)
    }

//    def save() { }
//    def update() { }
//    def delete() { }
//    def get() { }

}
