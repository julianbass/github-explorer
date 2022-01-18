export class IssuesList {
    render(issues) {
            const el = document.querySelector(".repo-selected-info");
            el.classList.add("show");

            const html = `<h3>Issues</h3>            
            ${issues
              .map((issue, index) => {
                console.log(issue);
                return `
                <div id="issues-${index}" class="issues-details">
                    <p>Title: ${issue.title} </p>
                    <p>State: ${issue.state}</p>
                    <p>User: ${issue.user}</p>
                    <p>Body:  ${issue.body}</p>
                </div>
            `;
              })
              .join("\n")}`;

    el.innerHTML = html;

    this.bindEventListeners();
  }

  bindEventListeners() {}

  update(issues) {
    this.render(issues);
  }
}