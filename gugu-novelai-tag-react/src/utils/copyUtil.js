export const copyToClipboard = (text) => {
    let result;
    if (navigator.clipboard) {
        result = navigator.clipboard.writeText(text);
    } else if (document.queryCommandSupported && document.queryCommandSupported('copy')) {
        const textarea = document.createElement('textarea');
        textarea.textContent = text;
        textarea.style.position = 'fixed'; // Prevent scrolling to bottom of page in MS Edge.
        document.body.appendChild(textarea);
        textarea.select();
        try {
            result = document.execCommand('copy');
        } catch (err) {
            console.error('Failed to copy text: ', err);
        } finally {
            document.body.removeChild(textarea);
        }
    } else {
        console.error('Your browser does not support the Clipboard API or the copy command');
    }
    return result;
};

