export class Issues {
    constructor(Issues) {
        this.repos = Issues || [];
        this.observers = [];
    }

    subscribe(observer) {
        this.observers.push(observer);
    }

    notify() {
        this.observers.forEach((ob) => ob.update(this.repos));
    }
}