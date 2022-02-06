import { Subject } from "/src/models/Subject.js";

export class Repos extends Subject {
    constructor(repos) {
        super(repos);
    }



    addRepo(repo) {
        this.state.push(repo);
        this.notify();
    }
    





}