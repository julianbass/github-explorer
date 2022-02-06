export class Subject {
  constructor(state) {
    this.observers = [];
    this.state = state;
  }

  subscribe(observer) {
    this.observers.push(observer);
  }

  unscubscribe(observer) {
    this.observers = this.observers.filter(o => o === observer);
}

  notify(state) {
    this.observers.forEach((observer) =>
      observer.update(state || this.state)
    );
  }


}
