const FIELD = {
    active: 'active',
    hidden: 'hidden',
    x: 'x',
    o: 'o'
};

let elems = [];
let canClick = false;

const transitionMs = 200;
const winCount = 5;
const initialRows = 3;
const initialCols = 3;

let game;

window.onload = () => {
    game = document.getElementById('game');
    drawField();

    /*
    setInterval(() => {
        function choose(choices) {
            let index = Math.floor(Math.random() * choices.length);
            return choices[index];
        }
        let elem = choose(Array.from(game.childNodes).filter(e => e.dataset.state === FIELD.active));
        elem.onclick();
    }, 10);
    */
};

window.onresize = () => drawField();

function createCell() {
    let newElem = document.createElement('div');
    newElem.className = 'cell';
    newElem.dataset.state = FIELD.hidden;
    newElem.onclick = () => clickedOnElem(newElem);
    let outerDiv = document.createElement('div');
    let innerDiv = document.createElement('div');
    outerDiv.appendChild(innerDiv);
    newElem.appendChild(outerDiv);
    game.appendChild(newElem);
    return newElem;
}

function isClicked(cell) {
    return cell.dataset.state === FIELD.x || cell.dataset.state === FIELD.o;
}

function createInitialField() {
    if (elems.length !== 0) {
        game.innerHTML = '';
    }

    for (let row = 0; row < initialRows; row++) {
        let currRowElems = [];
        for (let col = 0; col < initialCols; col++) {
            let newElem = createCell();
            newElem.dataset.state = FIELD.active;
            currRowElems.push(newElem);
        }
        elems.push(currRowElems);
    }

    game.dataset.move = FIELD.x;
    canClick = true;
}

function clickedOnElem(elem) {
    let state = elem.dataset.state;
    if (!canClick || state === FIELD.hidden || state === FIELD.x || state === FIELD.o) {
        return;
    }

    canClick = false;

    if (game.dataset.move === FIELD.x) {
        game.dataset.move = FIELD.o;
        elem.dataset.state = FIELD.x;

    } else if (game.dataset.move === FIELD.o) {
        game.dataset.move = FIELD.x;
        elem.dataset.state = FIELD.o;
    }

    if (checkWin()) {
        drawField();
        return;
    }

    if (expandField()) {
        drawField();
        setTimeout(() => {
            unhideField();
            drawField();
            canClick = true;
        }, transitionMs);
    } else {
        unhideField();
        drawField();
        canClick = true;
    }
}

function checkWin() {
    function isValid(oldX, oldY, newX, newY) {
        if (newX < 0 || newY < 0 || newX >= elems.length || newY >= elems[0].length
            || oldX < 0 || oldY < 0 || oldX >= elems.length || oldY >= elems[0].length) {
            return false;
        }
        return elems[oldX][oldY].dataset.state === elems[newX][newY].dataset.state;
    }

    function findWin(cell, x, y, dx, dy) {
        let similar = 1;
        for (let curr = 1; curr < winCount; curr++) {
            if (!isValid(x, y, x + curr * dx, y + curr * dy)) {
                break;
            }
            similar++;
        }
        if (similar === winCount) {
            for (let curr = 0; true; curr++) {
                if (!isValid(x, y, x + curr * dx, y + curr * dy)) {
                    break;
                }
                elems[x + curr * dx][y + curr * dy].dataset.win = 'true';
            }
            game.dataset.move = 'end';
            return true;
        }
        return false;
    }

    let rows = elems[0].length;
    let cols = elems.length;
    for (let x = 0; x < cols; x++) {
        for (let y = 0; y < rows; y++) {
            let cell = elems[x][y];
            if(isClicked(cell)
                && (findWin(cell, x, y, 1, 0)
                || findWin(cell, x, y, 0, 1)
                || findWin(cell, x, y, 1, 1))) {
                return true;
            }
        }
    }
}

function expandField() {
    function expandTop() {
        elems.unshift([]);
        for (let col = 0; col < elems[1].length; col++) {
            elems[0].push(createCell());
        }
    }

    function expandBottom() {
        elems.push([]);
        for (let col = 0; col < elems[0].length; col++) {
            elems[elems.length - 1].push(createCell());
        }
    }

    function expandLeft() {
        for (let row = 0; row < elems.length; row++) {
            elems[row].unshift(createCell());
        }
    }

    function expandRight() {
        for (let row = 0; row < elems.length; row++) {
            elems[row].push(createCell());
        }
    }

    let rows = elems.length;
    let cols = elems[0].length;

    for (let col = 0; col < cols; col++) {
        let topElem = elems[0][col];
        if (isClicked(topElem)) {
            expandTop();
            if (col === 0) {
                expandLeft();
            } else if (col === cols - 1) {
                expandRight();
            }
            return true;
        }
        let bottomElem = elems[rows - 1][col];
        if (isClicked(bottomElem)) {
            expandBottom();
            if (col === 0) {
                expandLeft();
            } else if (col === cols - 1) {
                expandRight();
            }
            return true;
        }
    }

    for (let row = 1; row < rows - 1; row++) {
        let leftElem = elems[row][0];
        if (isClicked(leftElem)) {
            expandLeft();
            return true;
        }
        let rightElem = elems[row][cols - 1];
        if (isClicked(rightElem)) {
            expandRight();
            return true;
        }
    }

    return false;
}

function unhideField() {
    let rows = elems.length;
    let cols = elems[0].length;
    for (let x = 0; x < rows; x++) {
        for (let y = 0; y < cols; y++) {
            let elem = elems[x][y];
            if (elem.dataset.state !== FIELD.hidden) {
                continue;
            }
            for (let dx of [-1, 0, 1]) {
                for (let dy of [-1, 0, 1]) {
                    let newX = x + dx;
                    let newY = y + dy;
                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && isClicked(elems[newX][newY])) {
                        elem.dataset.state = FIELD.active;
                    }
                }
            }
        }
    }
}

function drawField() {
    if (elems.length === 0) {
        createInitialField();
    }

    let clientHeight = document.documentElement.clientHeight;
    let clientWidth = document.documentElement.clientWidth;

    let maxHeight = clientHeight * 0.9;
    let maxWidth = clientWidth * 0.9;
    let freeSpaceLeft = (clientWidth - maxWidth) / 2;
    let freeSpaceTop = (clientHeight - maxHeight) / 2;

    let maxCellSize = 100;

    let rows = elems.length;
    let cols = elems[0].length;

    let computedWidth = cols * maxCellSize;
    let computedHeight = rows * maxCellSize;

    if (computedWidth > maxWidth) {
        computedHeight *= maxWidth / computedWidth;
        computedWidth *= maxWidth / computedWidth;
    }

    if (computedHeight > maxHeight) {
        computedWidth *= maxHeight / computedHeight;
        computedHeight *= maxHeight / computedHeight;
    }

    let shiftLeft = (maxWidth - computedWidth) / 2;
    let shiftTop = (maxHeight - computedHeight) / 2;

    let cellSize = Math.min(computedWidth / cols, computedHeight / rows);

    for (let row = 0; row < rows; row++) {
        for (let col = 0; col < cols; col++) {
            let elem = elems[row][col];
            elem.style.top = (freeSpaceTop + shiftTop + row * cellSize) + 'px';
            elem.style.left = (freeSpaceLeft + shiftLeft + col * cellSize) + 'px';
            elem.style.width = cellSize + 'px';
            elem.style.height = cellSize + 'px';
        }
    }
}
