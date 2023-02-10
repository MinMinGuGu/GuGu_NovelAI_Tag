export const fetchData = (url, options = {headers: {'content-type': 'application/json',}}) => {
    return fetch(url, options)
        .then(response => {
            if (!response.ok) {
                throw new Error(`${response.status}: ${response.statusText}`);
            }
            return response.json();
        })
        .catch(error => {
            // console.error('Request failed: ', error);
            throw error;
        });
};

export const get = (url, options = {headers: {'content-type': 'application/json',}}) => {
    return fetchData(url, {...options, method: 'GET'});
};

export const post = (url, body, options = {headers: {'content-type': 'application/json',}}) => {
    return fetchData(url, {...options, method: 'POST', body: JSON.stringify(body)});
};

export const put = (url, body, options = {headers: {'content-type': 'application/json',}}) => {
    return fetchData(url, {...options, method: 'PUT', body: JSON.stringify(body)});
};

export const del = (url, options = {headers: {'content-type': 'application/json',}}) => {
    return fetchData(url, {...options, method: 'DELETE'});
};
