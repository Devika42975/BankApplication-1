const URL = "http://localhost:8080/api/banks";

// Save Bank
document.getElementById("bankForm").addEventListener("submit", async (e) => {

    e.preventDefault();

    const bankName = document.getElementById("bankName").value;
    const ifscCode = document.getElementById("ifscCode").value;

    await fetch(URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            bankName,
            ifscCode
        })
    });

    alert("Bank Saved!");

    document.getElementById("bankForm").reset();

    loadData();
});

// Save Account
document.getElementById("accountForm").addEventListener("submit", async (e) => {

    e.preventDefault();

    const bankId = document.getElementById("bankSelect").value;
    const accountHolderName = document.getElementById("holderName").value;
    const balance = Number(document.getElementById("balance").value);

    await fetch(`${URL}/${bankId}/accounts`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            accountHolderName,
            balance
        })
    });

    alert("Account Saved!");

    document.getElementById("accountForm").reset();

    loadData();
});

// Load Data
async function loadData() {

    const res = await fetch(URL);
    const data = await res.json();

    const select = document.getElementById("bankSelect");
    const display = document.getElementById("display");

    select.innerHTML = `<option value="">Select Bank</option>`;
    display.innerHTML = "";

    data.forEach(bank => {

        select.innerHTML += `
            <option value="${bank.id}">
                ${bank.bankName}
            </option>
        `;

        let html = `
            <div class="bank-box">
                <h3>${bank.bankName}</h3>
                <p>IFSC : ${bank.ifscCode}</p>
        `;

        if (bank.accounts && bank.accounts.length > 0) {

            html += "<h4>Accounts</h4>";

            bank.accounts.forEach(account => {

                html += `
                    <p>
                        ${account.accountHolderName}
                        - ₹${account.balance}
                    </p>
                `;
            });

        } else {

            html += `<p>No Accounts</p>`;
        }

        html += `</div>`;

        display.innerHTML += html;
    });
}

loadData();