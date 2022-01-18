export class Repo {
    constructor(repo) {
        this.repo = repo;
        this.observers = [];
    }

    subscribe(observer) {
        this.observers.push(observer);
    }

    notify() {
        this.observers.forEach((ob) => ob.update(this.repo));
    }
}