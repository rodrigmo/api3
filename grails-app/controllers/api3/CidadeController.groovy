package api3

import api3.LogService
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

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

    RestBuilder rest = new RestBuilder()
    String baseURL = "http://localhost:8080/api2/cidade/"

    def list() {
        String finalURL = baseURL+"list/"
        RestResponse resReq = rest.get(finalURL)
        JSONArray responseJson = resReq.json as JSONArray

        respond(responseJson, status: resReq.status)
    }

    def get(Long id) {
        String finalURL = baseURL+"get/"+"?id=${id}"
        RestResponse resReq = rest.get(finalURL)
        JSONObject responseJson = resReq.json as JSONObject

        respond(responseJson, status: resReq.status)
    }

    def save() {
        LogService logService = new LogService()
        String finalURL = baseURL+"save/"
        RestResponse resReq = rest.post(finalURL) {json(nome: request.JSON.nome)}
        JSONObject responseJson = resReq.json as JSONObject

        logService.salvarLog(request, responseJson)

        respond(responseJson, status: resReq.status)
    }
    def update(Long id) {
        LogService logService = new LogService()
        String finalURL = baseURL+"update/"+"?id=${id}"
        RestResponse resReq = rest.put(finalURL) {json(nome: request.JSON.nome)}
        JSONObject responseJson = resReq.json as JSONObject

        logService.salvarLog(request, responseJson)

        respond(responseJson, status: resReq.status)
    }

    def delete(Long id) {
        LogService logService = new LogService()
        String finalURL = baseURL+"delete/"+"?id=${id}"
        RestResponse resReq = rest.delete(finalURL)
        JSONObject responseJson = resReq.json as JSONObject

        logService.salvarLog(request, responseJson)

        respond(responseJson, status: resReq.status)
    }

}
