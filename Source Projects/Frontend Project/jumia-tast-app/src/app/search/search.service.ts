import { Injectable } from "@angular/core";

import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { map } from "rxjs/operators";
import { environment } from "../../environments/environment"

@Injectable({
    providedIn: "root",
})
export class SearchService {
    baseUrl = environment.baseUrl;
    SEARCH_CUSTOMERS_URL: string =
        this.baseUrl + "/api/customer/search";

    GET_COUNTRIES_URL: string =
        this.baseUrl + "/api/country/all";
    constructor(private httpClient: HttpClient) { }

    public getCustomersByFilters(country, state) {

        var fullUrl = `${this.SEARCH_CUSTOMERS_URL}?country=${country}&state=${state}`;
        let headers = new HttpHeaders({ "Content-Type": "application/json" });

        return this.httpClient
            .get(fullUrl, { headers: headers })
            .pipe(map((response: any) => response));
    }

    public getAllCountries() {
        let headers = new HttpHeaders({ "Content-Type": "application/json" });
        return this.httpClient
            .get(this.GET_COUNTRIES_URL, { headers: headers })
            .pipe(map((response: any) => response));
    }

}
