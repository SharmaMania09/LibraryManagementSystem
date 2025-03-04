async function fetchProtectedData(endpoint) 
{
    const token = localStorage.getItem("token"); // Retrieve token from localStorage

    if (!token) 
    {
        alert("No token found, redirecting to login...");
        window.location.href = "/users/login"; // Redirect if no token
        return;
    }

    const response = await fetch(endpoint, 
    {
        method: "GET",
        headers: 
        {
            "Authorization": "Bearer " + token, // Attach token
            "Content-Type": "application/json"
        }
    });

    if (response.ok) 
    {
        return await response.json();
    } 
    else 
    {
        console.log("Unauthorized Access");
        localStorage.removeItem("token"); // Remove invalid token
        window.location.href = "/users/login"; // Redirect to login
    }
}
