# StaticLayoutView
a pre-render TextView demo

This demo use StaticLayout to render text in a custom TextView and create the StaticLayout in another thread before enter the UI, and these StaticLayout draw in a dummy canvas which warm up the TextLayoutCache in the system. And these optimization improve the performance of showing complicated text(which may include many spans, emoji) in a ListView 

for the detail of this projec, please read this post(in chinese): [http://ragnraok.github.io/textview-pre-render-research.html](http://ragnraok.github.io/textview-pre-render-research.html)
