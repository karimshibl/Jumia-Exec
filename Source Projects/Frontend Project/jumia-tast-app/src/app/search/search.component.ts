import { Component, OnInit } from '@angular/core';
// import { FormBuilder, FormControl, FormGroup, Validators, } from '@angular/forms';
import { SearchService } from './search.service';

declare var alertify: any;

@Component({
    selector: 'app-search',
    templateUrl: './search.component.html',
    styleUrls: ['./search.component.css'],
    providers: [SearchService]
})

export class SearchComponent implements OnInit {
    message: string = '';
    countriesList: Array<any>;
    searchResults: Array<any>;
    searchInProgress: boolean = false;

    //sorting
    key: string = 'name'; //set default
    reverse: boolean = false;
    sort(key) {
        this.key = key;
        this.reverse = !this.reverse;
    }

    //initializing p to one
    p: number = 1;

    ngOnInit() {
    }

    openPopup() {
        debugger;
        alertify.alert(this.message);
    }

    constructor(private searchService: SearchService) {
        this.getCountries();
    }

    getCountries() {
        this.searchService
            .getAllCountries()
            .subscribe(
                (response) => {
                    debugger;
                    var status = response['status'];
                    if (status.code != 200) {
                        this.message = status.message;
                        this.openPopup();
                    }
                    else {
                        this.message = "";
                        this.countriesList = response.data;
                    }

                },
                (error) => {
                    console.log(error);
                    this.message = 'No Countries Data found';
                    this.openPopup();
                }
            );
    }

    search(country, state): void {
        console.log("country = " + country);
        console.log("state = " + state);
        this.searchResults = null;
        this.message = null;
        this.searchInProgress = true;
        this.searchService
            .getCustomersByFilters(country, state)
            .subscribe(
                (response) => {
                    debugger;
                    var status = response['status'];
                    if (status.code != 200) {
                        this.message = status.message;
                        this.openPopup();
                    }
                    else {
                        this.message = "";
                        this.searchResults = response.data;
                    }
                    this.searchInProgress = false;

                },
                (error) => {
                    console.log(error);
                    this.searchInProgress = false;
                    this.message = 'No Data found';
                    this.openPopup();
                }
            );

    }

}
