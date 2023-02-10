const getRandomColor = () => {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
};

const usedColors = new Set();

export const getUniqueRandomColor = () => {
    let color;
    do {
        color = getRandomColor();
    } while (usedColors.has(color));
    usedColors.add(color);
    return color;
};
