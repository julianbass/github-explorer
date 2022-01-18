export class Repos {
    constructor(repos) {
        this.repos = repos || [];
        this.observers = [];
    }


    subscribe(observer) {
        this.observers.push(observer);
    }

    notify() {
        this.observers.forEach(ob => ob.update(this.repos));
    }


    addRepo(repo) {
        this.repos.push(repo);
        this.notify();
    }





}