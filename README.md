# StaticLayoutView
a pre-render TextView demo

this demo use StaticLayout to render text in a custom TextView and created the StaticLayout in another thread before enter the UI, and these draw StaticLayout in a dummy canvas which warm up the TextLayoutCache, which improve the performance of showing complicated text(which may include many spans, emoji) in a ListView 