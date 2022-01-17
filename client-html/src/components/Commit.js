

export class Commit {

   constructor(commits) {
       this.commits = commits;
   }
    
  render() {
    const commitPanel = document.querySelector(".commits-panel");
    commitPanel.innerHTML = "";
    commitPanel.classList.add("show");

    this.commits.forEach((commit) => {
      console.log(commit);
      const commitWrapper = document.createElement("div");
      commitWrapper.classList.add("commit-details");
      const createdOn = document.createElement("p");
      const authorName = document.createElement("p");
      const authorEmail = document.createElement("p");
      const message = document.createElement("p");

      createdOn.innerHTML =
        "Created On: " + new Date(commit.createdOn).toDateString();
      authorName.innerHTML = "Author Name: " + commit.author.name;
      authorEmail.innerHTML = "Author Email: " + commit.author.email;
      message.innerHTML = "Commit Message: " + commit.message;

      commitWrapper.append(...[createdOn, authorName, authorEmail, message]);
      commitPanel.appendChild(commitWrapper);
    });
  }
}
