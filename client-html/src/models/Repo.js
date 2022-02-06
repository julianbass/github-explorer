import { Subject } from "/src/models/Subject.js";

export class Repo extends Subject {
    constructor(repo) {    
        super(repo);           
       
    }  

    getName() {
        return this.state.name;
    }

    getOwner() {
        return this.state.owner;
    }

    setRepo(repo) {
        this.state = repo;
        this.notify();
    }
}